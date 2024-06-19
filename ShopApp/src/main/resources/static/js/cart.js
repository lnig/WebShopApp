document.addEventListener("DOMContentLoaded", () => {
    const addToCartButtons = document.querySelectorAll(".add-to-cart-btn");
    const cartList = document.querySelector(".cart-list");

    let cart = JSON.parse(localStorage.getItem("cart")) || [];
    localStorage.setItem("cart", JSON.stringify(cart));

    addToCartButtons.forEach(button => {
        button.addEventListener("click", () => {
            const productId = parseInt(button.getAttribute("data-product-id"));
            const productName = button.getAttribute("data-product-name");
            const productDescription = button.getAttribute("data-product-description");
            const productPrice = parseFloat(button.getAttribute("data-product-price"));
            addToCart(productId, productName, productDescription, productPrice);
            displayCart();
        });
    });

    displayCart();
});

function addToCart(productId, productName, productDescription, productPrice) {
    const cart = JSON.parse(localStorage.getItem("cart"));
    const product = cart.find(product => product.id === productId);
    if (product) product.quantity += 1;
    else cart.push({ id: productId, name: productName, description: productDescription, price: productPrice, quantity: 1 });
    localStorage.setItem("cart", JSON.stringify(cart));
}

function removeFromCart(productId) {
    let cart = JSON.parse(localStorage.getItem("cart"));
    cart = cart.filter(product => product.id !== productId);
    localStorage.setItem("cart", JSON.stringify(cart));
    displayCart();
}

function increaseQuantity(productId) {
    const cart = JSON.parse(localStorage.getItem("cart"));
    const product = cart.find(product => product.id === productId);
    if (product) product.quantity += 1;
    localStorage.setItem("cart", JSON.stringify(cart));
    displayCart();
}

function decreaseQuantity(productId) {
    const cart = JSON.parse(localStorage.getItem("cart"));
    const product = cart.find(product => product.id === productId);
    if (product && product.quantity > 1) {
        product.quantity -= 1;
        localStorage.setItem("cart", JSON.stringify(cart));
    } else {
        removeFromCart(productId);
    }
    displayCart();
}

function displayCart() {
    const cart = JSON.parse(localStorage.getItem("cart"));
    const cartList = document.querySelector(".cart-list");
    cartList.innerHTML = "";
    let totalPrice = 0;

    cart.forEach(product => {
        const productElement = document.createElement("div");
        productElement.classList.add("product");

        const productNameElement = document.createElement("p");
        productNameElement.classList.add("product-name");
        productNameElement.innerText = product.name;

        const productDescriptionElement = document.createElement("p");
        productDescriptionElement.classList.add("product-description");
        productDescriptionElement.innerText = product.description;

        const productPriceElement = document.createElement("p");
        productPriceElement.classList.add("product-price");
        productPriceElement.innerText = `$${(product.price * product.quantity).toFixed(2)}`;

        const productQuantityElement = document.createElement("div");
        productQuantityElement.classList.add("product-quantity");

        const decreaseButton = document.createElement("button");
        decreaseButton.classList.add("quantity-button");
        decreaseButton.innerText = "-";
        decreaseButton.addEventListener("click", () => decreaseQuantity(product.id));

        const quantityDisplay = document.createElement("span");
        quantityDisplay.innerText = product.quantity;

        const increaseButton = document.createElement("button");
        increaseButton.classList.add("quantity-button");
        increaseButton.innerText = "+";
        increaseButton.addEventListener("click", () => increaseQuantity(product.id));

        productQuantityElement.appendChild(decreaseButton);
        productQuantityElement.appendChild(quantityDisplay);
        productQuantityElement.appendChild(increaseButton);

        const removeButton = document.createElement("button");
        removeButton.classList.add("remove-button");
        removeButton.innerText = "Remove";
        removeButton.addEventListener("click", () => removeFromCart(product.id));

        productElement.appendChild(productNameElement);
        productElement.appendChild(productDescriptionElement);
        productElement.appendChild(productPriceElement);
        productElement.appendChild(productQuantityElement);
        productElement.appendChild(removeButton);

        cartList.appendChild(productElement);

        totalPrice += product.price * product.quantity;
    });

    const totalPriceElement = document.createElement("div");
    totalPriceElement.classList.add("total-price");
    totalPriceElement.innerText = `Total Price: $${totalPrice.toFixed(2)}`;
    cartList.appendChild(totalPriceElement);

    document.getElementById("totalPrice").value = totalPrice.toFixed(2);
    document.getElementById("cartItems").value = JSON.stringify(cart);
}
