package it.polimi.ingsw.ps31.model.gameResource;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Giuseppe on 10/05/2017.
 */
public class ResourceList {
    private List<Resource> resourceList = new ArrayList<>();           //TODO da provare/verificare come funziona

    /* Constructor */
    public ResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }
    public ResourceList(Resource resource)
    {
        this.resourceList.add(resource);
    }
    public ResourceList(Resource resource1,Resource resource2)
    {
        this.resourceList.add(resource1);
        this.resourceList.add(resource2);
    }

    public ResourceList(){}

    //metodi tipici delle liste
    public void addSpecificResource(Resource resource){
        boolean found=false;

        for (Resource resourceList : this.resourceList) {
            if (resourceList.getClass().equals(resource.getClass()) && !found) {
                resourceList.addValue(resource.getValue());
                found = true;
            }
        }
        if(!found){
            this.resourceList.add(resource);
        }
    }
    public void subSpecificResource(Resource resource)
    {
        boolean found=false;

        for (Resource resourceList : this.resourceList) {

            if (resourceList.getClass().equals(resource.getClass()) && !found) {
                resourceList.subValue(resource.getValue());
                found = true;
            }
        }
        if(!found){
            // todo error:!
        }
    }
    public void discountSpecificResource(Resource resource)
    {
        for (Resource resourceList : this.resourceList) {

            if (resourceList.getClass().equals(resource.getClass())) {
                resourceList.discountValue(resource.getValue());
            }
        }
    }
    
    public Resource remove(int index){
        return this.resourceList.remove(index);
    }
    public Resource get(int index){
        return this.resourceList.get(index);
    }
    public void clear(){
        this.resourceList.clear();
    }
    public int size(){
        return this.resourceList.size();
    }

    public List<Resource> getResourceList(){
        return new ArrayList<>(this.resourceList);
    }

    public void multiplyResourceList(int factor){

        for(int i=0; i<this.resourceList.size(); i++) {
            this.resourceList.get(i).multvalue(factor);
        }
    }
    public Resource getSpecificResource(Class<? extends Resource> resourceClass) throws NullPointerException{
        for(int i=0; i<this.resourceList.size();i++){
            if(resourceClass.equals(resourceList.get(i).getClass())){
                return resourceList.get(i);
            }
        }
        return null; //non trovato
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceList that = (ResourceList) o;

        return resourceList != null ? resourceList.equals(that.resourceList) : that.resourceList == null;
    }

    @Override
    public int hashCode() {
        return resourceList != null ? resourceList.hashCode() : 0;
    }

    public boolean lessOrEquals(Object o){     //confronto tra due liste di risorse per sapere se una è minore o uguale dell altra
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceList that = (ResourceList) o;

        if(that.getResourceList().size()<this.getResourceList().size()){            //se la mia lista ha più elemnti non sarà mai minore
            return false;
        }
        int contatore=0;
        for(int i=0; i<that.resourceList.size();i++){
            for(int j=0; j<this.getResourceList().size();j++){
                if(this.resourceList.get(j).lessOrEquals(that.getResourceList().get(i))){// confronto i vari elementi della lista con il metodo che ho implementato nel confronto tra risorse
                    contatore++;
                }
            }
        }
        if(contatore==this.getResourceList().size()){          // se tutte le mie risorse erano minore delle altre allora la mia lista è confrontabile ed è minore dell altra
            return true;
        }

        return false;
    }

   public int getPhysicalResource(){
        int physicalResource =0;
        for(int i=0;i<resourceList.size();i++){
            physicalResource= physicalResource + resourceList.get(i).getPhysicalResourceValue();
        }
        return physicalResource;
   }
    public int getPointResource(){
        int pointResource =0;
        for(int i=0;i<resourceList.size();i++){
            pointResource= pointResource + resourceList.get(i).getPointResourceValue();
        }
        return pointResource;
    }

    @Override
    public String toString() {
        String resourceString = "";
        for (Resource resource : resourceList
                ) {
            resourceString = resourceString +resource.toString();
        }
        return resourceString;
    }
}
