const addToCartButtons = document.querySelectorAll(".add-to-cart-btn");

let cart = JSON.parse(localStorage.getItem("cart"));
if(!cart){
    cart = [];
    localStorage.setItem("cart", JSON.stringify(cart));
}
addToCartButtons.forEach(button => {
    button.addEventListener("click", () => {
        const productId = parseInt(button.getAttribute("data-product-id"));
        const productName = parseInt(button.getAttribute("data-product-name"));
        const productDescription = parseInt(button.getAttribute("data-product-description"));
        const productPrice = parseInt(button.getAttribute("data-product-price"));
        addToCart(productId);
        console.log(JSON.parse(localStorage.getItem("cart")));
    })
})

function addToCart(productId, productName, productDescription, productPrice) {
    const cart = JSON.parse(localStorage.getItem("cart"));
    const product = cart.find(product => product.id === productId);
    if(product) product.quantity += 1;
    else cart.push({id: productId, name: productName, description: productDescription, price: productPrice, quantity: 1});
    localStorage.setItem("cart", JSON.stringify(cart));
}

function displayCart(){
    const cart = JSON.parse(localStorage.getItem("cart"));
    const cartList = document.querySelector(".cart-list");
    cart.forEach(product => {
        const productElement = document.createElement("div");
        productElement.classList.add("product");
        productElement.innerHTML = `
            <h3>${product.name}<h3>
            <p>Description: ${product.description}</p>
            <p>Price: ${product.price}</p>
            <p>Quantity: ${product.quantity}</p>
        `;
        cartList.appendChild(productElement);
    })
}

document.addEventListener("DOMContentLoaded", () => {
    if(window.location.pathname === "/cart"){
        displayCart();
    }
});