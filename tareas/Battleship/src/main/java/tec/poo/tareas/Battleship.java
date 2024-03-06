package tec.poo.tareas;

public class Battleship{
    
    private String name; 
    private int size;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setSize(int size){
        this.size = size;
    }

    public int getSize(){
        return size;
    }

    public void hit(){
        this.size = this.size - 1;
    }

    public boolean isDead(){
        return this.size == 0;
    }

}