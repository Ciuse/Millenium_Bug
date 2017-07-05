package it.polimi.ingsw.ps31.model.player;

/**
 * Created by Francesco on 15/05/2017.
 */
//public class PlayerResources {
////
//    private ResourceList playerResourceList= new ResourceList();
//
//    /* Constructor */
//
//    public PlayerResources() {
//    }
//
//    public PlayerResources(ResourceList resourceList) {
//        this.playerResourceList=resourceList;
//    }
//    public PlayerResources(List<Resource> resourceList) {
//       playerResourceList=new ResourceList(resourceList);
//    }
//
//    /* Setters & Getters */
//    public void addResources(Resource resourceToAdd)
//    {
//        //Aggiungo la risorsa
//        this.playerResourceList.addSpecificResource(resourceToAdd);
//
//    }
//
//    public void subResources(Resource resourceToSub)
//    {
//        //Aggiungo la risorsa
//        this.playerResourceList.subSpecificResource(resourceToSub);
//    }
//
//    public int getResourceValue(Class<? extends Resource> resourceClass) throws NullPointerException{
//        return playerResourceList.getSpecificResource(resourceClass).getValue();
//    }
//
//    public Resource getSpecificResource(Class<? extends Resource> resourceClass) throws NullPointerException {
//        return playerResourceList.getSpecificResource(resourceClass);
//    }
//    public ResourceList getPlayerResourceList(){
//        return new ResourceList(playerResourceList.getListOfResource());
//    }
//
//    public boolean greaterOrEquals(ResourceList that){
//        int contatore=0;
//        return that.lessOrEquals(playerResourceList);
////        for(int i=0; i<this.playerResourceList.size();i++){
////            for(int j=0; j<that.size();j++){
////                if(that.get(j).lessOrEquals(this.playerResourceList.get(i))){// confronto i vari elementi della lista con il metodo che ho implementato nel confronto tra risorse
////                    contatore++;
////                }
////            }
////        }
////        if(contatore==that.size()){          // se tutte le mie risorse erano minore delle altre allora la mia lista è confrontabile ed è minore dell altra
////            return true;
////        }
////
////        return false;
//    }
//    public boolean greaterOrEquals(Resource resource){
//        ResourceList that = new ResourceList(resource);
//        return greaterOrEquals(that);
//    }
//}
