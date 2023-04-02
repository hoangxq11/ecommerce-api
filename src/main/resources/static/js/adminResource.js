function getProduct(callback) {
    const productApi = "http://localhost:8080/api/admin/product";
    fetch(productApi)
        .then(function (response){
            return response.json();
        })
        .then(callback);
}