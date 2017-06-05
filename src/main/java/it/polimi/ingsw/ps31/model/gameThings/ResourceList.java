package it.polimi.ingsw.ps31.model.gameThings;

import java.util.*;

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
    public ResourceList(){}

    //metodi tipici delle liste
    public void addSpecificResource(Resource resource){
        boolean found=false;

        for(int i=0; i<this.resourceList.size(); i++) {
            if (this.resourceList.get(i).getClass().equals(resource.getClass()) && found == false) {
                this.resourceList.get(i).addValue(resource.getValue());
                found = true;
            }
        }
        if(found==false){
            this.resourceList.add(resource);
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

    /*Getters & Setters*/
    public void setCoin() {
        //METODI PER INSERIRE LE RISORSE NELLA CREAZIONE DELLE CARTE (NON SARANNO USATI NEl GIOCO)
        System.out.println("Inserisci il valore delle monete: ");
        Scanner scanner =new Scanner(System.in);
        int value= scanner.nextInt();
        Coin coin= new Coin(0);
        coin.addValue(value);
        if (coin.getValue()!=0) {
            this.resourceList.add(coin);
        }
    }
    public void setWood() {                                                 //METODI PER INSERIRE LE RISORSE NELLA CREAZIONE DELLE CARTE (NON SARANNO USATI NEl GIOCO)
        System.out.println("Inserisci il valore dei legni: ");
        Scanner scanner =new Scanner(System.in);
        int value= scanner.nextInt();
        Wood wood= new Wood(0);
        wood.addValue(value);
        if (wood.getValue()!=0) {
            this.resourceList.add(wood);
        }
    }
    public void setStone() {                                                 //METODI PER INSERIRE LE RISORSE NELLA CREAZIONE DELLE CARTE (NON SARANNO USATI NEl GIOCO)
        System.out.println("Inserisci il valore delle pietre: ");
        Scanner scanner =new Scanner(System.in);
        int value= scanner.nextInt();
        Stone stone= new Stone(0);
        stone.addValue(value);
        if (stone.getValue()!=0) {
            this.resourceList.add(stone);
        }
    }
    public void setServant() {                                                 //METODI PER INSERIRE LE RISORSE NELLA CREAZIONE DELLE CARTE (NON SARANNO USATI NEl GIOCO)
        System.out.println("Inserisci il valore dei servitori: ");
        Scanner scanner =new Scanner(System.in);
        int value= scanner.nextInt();
        Servant servant= new Servant(0);
        servant.addValue(value);
        if (servant.getValue()!=0) {
            this.resourceList.add(servant);
        }
    }
    public void setMilitaryStrength() {                                                 //METODI PER INSERIRE LE RISORSE NELLA CREAZIONE DELLE CARTE (NON SARANNO USATI NEl GIOCO)
        System.out.println("Inserisci il valore normale dei punti militari: ");
        Scanner scanner =new Scanner(System.in);
        int value= scanner.nextInt();
        System.out.println("Inserisci il valore minimo di punti militari richiesti : ");
        Scanner scanner1 = new Scanner(System.in);
        int valueRequest = scanner1.nextInt();
        MilitaryStrength militaryStrength= new MilitaryStrength(0,0);
        militaryStrength.addValue(value);
        militaryStrength.setValueRequest(valueRequest);
        if (militaryStrength.getValue()!=0) {
            this.resourceList.add(militaryStrength);
        }
    }
    public void setFaithPoint() {                                                 //METODI PER INSERIRE LE RISORSE NELLA CREAZIONE DELLE CARTE (NON SARANNO USATI NEl GIOCO)
        System.out.println("Inserisci il valore dei punti fede: ");
        Scanner scanner =new Scanner(System.in);
        int value= scanner.nextInt();
        FaithPoint faithPoint= new FaithPoint(0);
        faithPoint.addValue(value);
        if (faithPoint.getValue()!=0) {
            this.resourceList.add(faithPoint);
        }
    }
    public void setVictoryPoint() {                                                 //METODI PER INSERIRE LE RISORSE NELLA CREAZIONE DELLE CARTE (NON SARANNO USATI NEl GIOCO)
        System.out.println("Inserisci il valore dei punti vittoria: ");
        Scanner scanner =new Scanner(System.in);
        int value= scanner.nextInt();
        VictoryPoint victoryPoint= new VictoryPoint(0);
        victoryPoint.addValue(value);
        if (victoryPoint.getValue()!=0) {
            this.resourceList.add(victoryPoint);
        }
    }
    public void setCouncilPrivilege() {                                                 //METODI PER INSERIRE LE RISORSE NELLA CREAZIONE DELLE CARTE (NON SARANNO USATI NEl GIOCO)
        System.out.println("Inserisci il numero dei privilegi del consiglio: ");
        Scanner scanner =new Scanner(System.in);
        int value= scanner.nextInt();
        System.out.println("sono diversi?: ");
        boolean different= scanner.nextBoolean();
        CouncilPrivilege councilPrivilege= new CouncilPrivilege(0, false);
        councilPrivilege.addValue(value);
        councilPrivilege.setDifferent(different);
        if (councilPrivilege.getValue()!=0) {
            this.resourceList.add(councilPrivilege);
        }
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
}
