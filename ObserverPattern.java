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

- Below is the example of observers pattern for error handling in a test run framework

Here the stress infrastructure runs a workflow in large scale on hardware.
It updates a counter when it encounters failure in any operation it performs
This counter state is monitored in the Subject which notifies the right observers.

*/

interface IObserver{
    IErrorNotifier em;
    public void performAction();
}

class FatalError implements IMonitor{
    public void performAction(){
        Map<String,String> stateCounter = em.getCounter();
        for(Map.Entry<String,List<String>> entry: stateCounter.entrySet){
            if(entry.getKey().equalsIgnoreCase("Fatal")){
                System.out.println("The fatal erron seen is : ");
                entry.getValue().forEach(s -> {
                    System.out.println(s);
                });
            }
        }
    }
}


class NonFatalError implements IMonitor{
    public void performAction(){
        Map<String,String> stateCounter = em.getCounter();
        for(Map.Entry<String,List<String>> entry: stateCounter.entrySet){
            if(entry.getKey().equalsIgnoreCase("NonFatal")){
                System.out.println("The Non fatal erron seen is : ");
                entry.getValue().forEach(s -> {
                    System.out.println(s);
                });
            }
        }
    }
}


interface IErrorNotifier{
    Map<String,List<String>> stateCounter;
    List<IObserver> listOfIObserver;
    public void registerObserver(IObserver ob);
    public void removeObserver(IObserver ob);
    public void notifyObserver();
    
}

class StressErrorNotifier{
    
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
        if(stateCounter.)
    }
    
    public Map<String,String>  getCounter(){
        return stateCounter;
    }
    
    public void setCounter(String errorEvent, String errorDetails){
        if(stateCounter.containsKey(errorEvent)){
             List<String> existingDetails =  stateCounter.get(errorEvent);
             existingDetails.put(errorDetails);
        }
    }
}
