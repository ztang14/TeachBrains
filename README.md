Entry -- Search -- Adding to the cart -- Payment -- Address(compare) -- Review -- Exit

Entites:   user: id, email id, password
           cart: id, customer, cartitem, total price
           cartitem: id, quantity, product, price
           customer: id, first name, last name, email address, phone number, billaddress, shipping address, user
           products: id, name, image, price, category, description, manufacturer
           order: id, cart, customer, shipping address, billaddress, 
           bill address: id, address, city, state, zip, country, customer
           shipping address: id, address, city, state, zip, country, customer
           
Conroller : cart: get
            cartitem: add, remove
            order: create order
            product: add, delete, update, get
            resgistration: resgister
            
            question： create a method -- boolean canship -- save all zip in database -- if shipping address.zip in list return true -- (controller?)
            
            
