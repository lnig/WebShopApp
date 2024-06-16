const addToCartButtons = document.querySelectorAll(".add-to-cart-btn");

let cart = JSON.parse(localStorage.getItem("cart"));
if(!cart){
    cart = [];
    localStorage.setItem("cart", JSON.stringify(cart));
}
addToCartButtons.forEach(button => {
    button.addEventListener("click", () => {
        const productId = parseInt(button.getAttribute("data-product-id"));
        const productName = button.getAttribute("data-product-name");
        const productDescription = button.getAttribute("data-product-description");
        const productPrice = button.getAttribute("data-product-price");
        addToCart(productId, productName, productDescription, productPrice);
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

function removeFromCart(productId) {
    let cart = JSON.parse(localStorage.getItem("cart"));
    const product = cart.find(product => product.id === productId);
    if(product) {
        if(product.quantity === 1) cart = cart.filter(product => product.id !== productId);
        else product.quantity -= 1;
    }
    localStorage.setItem("cart", JSON.stringify(cart));
    displayCart();
}

function displayCart(){
    const cart = JSON.parse(localStorage.getItem("cart"));
    const cartList = document.querySelector(".cart-list");
    cartList.innerHTML = "";
    cart.forEach(product => {
        const productElement = document.createElement("div");
        productElement.classList.add("product");
        productElement.setAttribute("data-product-id", product.id.toString());

        const productNameElement = document.createElement("p");
        productNameElement.innerText = product.name;

        const productDescriptionElement = document.createElement("p");
        productDescriptionElement.innerText = product.description;

        const productPriceElement = document.createElement("p");
        productPriceElement.innerText = product.price;

        const productQuantityElement = document.createElement("p");
        productQuantityElement.innerText = product.quantity;

        productElement.appendChild(productNameElement);
        productElement.appendChild(productDescriptionElement);
        productElement.appendChild(productPriceElement);
        productElement.appendChild(productQuantityElement);

        cartList.appendChild(productElement);
    })
}

document.addEventListener("DOMContentLoaded", () => {
    if(window.location.pathname === "/cart"){
        displayCart();
    }
});