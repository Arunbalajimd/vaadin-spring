Put all your service code here. A service is a bunch of classes that put all your data modifications into a transactional
context. This can be to create a user, put an object on their wishlist (if you are writing a shop) or remove an
account. This means the more complex operations which can't be done with the simple CRUD-repository.

The best way to implement a service is to define a business interface, describing the specific operations done
within your application/company. This interface should be in no way a generic data interface but more an interface
which maps your business operations directly to your data layer. This enables you to be very modular with your view
layer and can easily build some kind of REST-Layer on top of your service layer or build a mobile application
using your service or REST-layer.

A simple example for a business interface can be like this (the cart object is persisted to the database):

public interface OrderService {

    public void clearCart(Cart cart);

    public void addToCart(Cart cart, BuyableObject object);

    public void order(Cart cart);

}