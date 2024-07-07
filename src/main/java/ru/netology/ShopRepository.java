package ru.netology;

public class ShopRepository {
    private Product[] products = new Product[0];
    /**
     * Вспомогательный метод для имитации добавления элемента в массив
     * @param current — массив, в который мы хотим добавить элемент
     * @param product — элемент, который мы хотим добавить
     * @return — возвращает новый массив, который выглядит, как тот, что мы передали,
     * но с добавлением нового элемента в конец
     */
    private Product[] addToArray(Product[] current, Product product){
        Product[] tmp = new Product[current.length +1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[current.length ] = product;
        return tmp;
    }

    public void add(Product product) throws AlreadyExistsException {
        for (Product prod : products){
            if(prod.getId() == product.getId()){
                throw new AlreadyExistsException(
                        "Элемент с таким ID: " + product.getId() + " уже сущевствует"
                );
            }
        }
        products = addToArray(products, product);
    }
    public Product[] findAll(){
        return products;
    }

    public Product findById(int id){
        for (Product product : products){
            if(product.getId() == id){
                return product;
            }
        }
        return null;
    }
    //метод удаления по id
    public void removeById(int id) throws NotFoundException{
        if(findById(id) == null){
            throw new NotFoundException(
                    "Element with id " + id + " not found"
            );
        }
        Product[] tmp = new Product[products.length -1];
        int copeToIndex = 0;
        for(Product product : products){
            if(product.getId() != id){
                tmp[copeToIndex] = product;
                copeToIndex++;
            }
        }
        if (copeToIndex == tmp.length){
        products = tmp;
        } else {
            throw new NotFoundException(
                    "Element with id " + id + " not found"
            );
        }
    }

    public Product[] getProducts(int id) {
        return products;
    }
}
