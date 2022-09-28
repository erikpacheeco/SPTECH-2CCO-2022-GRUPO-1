package petfinder.petfinderapi.entidades;

public interface Convertible<T> {
    
    // method who convert and return some DTO to equivalent Entity
    public T convert();

}
