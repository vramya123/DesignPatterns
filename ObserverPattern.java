/*

Observer Pattern (Publishers subscribers)
========================================
The observer pattern defines one to many dependency between objects so that when one object changes state, all of its dependents are notified and updated automatically.

- relationship representation

  ISubject                 (has a)              IObserver
  -registerObserver()                         - update()
  -removeObserver()
  - notifyObserver()
  
  (Implements)                                (Implements)

ConcreteSubject         (has a)             ConcreteObserver

- Below is the example of observers pattern for error notification in a test

Here the test updates a counter when it encounters failure in any operation it performs
This counter state is monitored in the Subject which notifies the right observers.


- Important Object Oriented Principles(OO principles) to learn from this pattern:-
1) Strive for loosely coupled designs between objects that interact

*/

import java.io.*;
import java.util.*;

interface IObserver{
    
    public void performAction(Map<String,List<String>> stateCounter);
}

class FatalError implements IObserver{
 
    
    public void performAction( Map<String,List<String>> stateCounter){
        for(Map.Entry<String,List<String>> entry: stateCounter.entrySet()){
            if(entry.getKey().equalsIgnoreCase("Fatal")){
                System.out.println("The fatal error seen is : ");
                System.out.println("===============================");
                entry.getValue().forEach(s -> {
                    System.out.println(s);
                });
            }
        }
    }
}


class NonFatalError implements IObserver{
   
    
    public void performAction( Map<String,List<String>> stateCounter){
        for(Map.Entry<String,List<String>> entry: stateCounter.entrySet()){
            if(entry.getKey().equalsIgnoreCase("NonFatal")){
                System.out.println("The Non fatal error seen is : ");
                System.out.println("===============================");
                entry.getValue().forEach(s -> {
                    System.out.println(s);
                });
            }
        }
    }
}


interface IErrorNotifier{
    
    public void registerObserver(IObserver ob);
    public void removeObserver(IObserver ob);
    public void notifyObserver();
    public void setCounter(String errorEvent, String errorDetails);
    public Map<String,List<String>>  getCounter();
    
}

class StressErrorNotifier implements IErrorNotifier{
    Map<String,List<String>> stateCounter;
    List<IObserver> listOfIObserver;
    
    public StressErrorNotifier(){
        stateCounter = new HashMap<>();
        listOfIObserver = new ArrayList<>();
    }
    
     public void registerObserver(IObserver ob){
         listOfIObserver.add(ob);
     }
    public void removeObserver(IObserver ob){
        listOfIObserver.remove(ob);
    }
    public void notifyObserver(){
        System.out.println("Inside notification method");
        if(!stateCounter.isEmpty()){
            for(IObserver ob : listOfIObserver){
                ob.performAction(stateCounter);
            }
        }else{
            System.out.println("The counter is empty");
            return;
        }
        
    }
    
    public Map<String,List<String>>  getCounter(){
        return stateCounter;
    }
    
    public void setCounter(String errorEvent, String errorDetails){
        if(stateCounter.containsKey(errorEvent)){
             List<String> existingDetails =  stateCounter.get(errorEvent);
             existingDetails.add(errorDetails);
             stateCounter.put(errorEvent,existingDetails);
        }else{
            stateCounter.put(errorEvent,new LinkedList<String>(Arrays.asList(errorDetails)));
        }
    }
    
}

public class Observer{
    
    public static void main(String[] args){
        IErrorNotifier errorNotifier = new StressErrorNotifier();
        
        errorNotifier.registerObserver(new FatalError());
        errorNotifier.registerObserver(new NonFatalError());
        
        errorNotifier.setCounter("Fatal","virtual machine crashed");
        errorNotifier.setCounter("NonFatal","virtual machine migrated to another host");
        errorNotifier.setCounter("Fatal","Host crashed");
        errorNotifier.setCounter("NonFatal","Dumps on host although its accessible");
        errorNotifier.notifyObserver();
    }
}

/*

O/p:-

Inside notification method
The fatal error seen is : 
===============================
virtual machine crashed
Host crashed
The Non fatal error seen is : 
===============================
virtual machine migrated to another host
Dumps on host although its accessible
*/
