
function addToList() {

    let item = document.getElementById("groceryItem").value;
    let amount = document.getElementById("amountOfItem").value;
    let depart = document.getElementById("department").value;

    switch (depart) {
        case 'Dry Goods':
        document.getElementById("dryGoods").innerHTML += `<br>${item}: ${amount}`;
        break;
        case 'Meat':
        document.getElementById("meats").innerHTML += `<br> ${item}: ${amount}`;
        break;
        case 'Produce':
        document.getElementById("produce").innerHTML += `<br> ${item}: ${amount}`;
        break;
        case 'Bread':
        document.getElementById("breads").innerHTML += `<br> ${item}: ${amount}`;
        break;
        case 'Dairy':
        document.getElementById("dairy").innerHTML += `<br> ${item}: ${amount}`;
        break;
        case 'Seafood':
        document.getElementById("seafood").innerHTML += `<br> ${item}: ${amount}`;
        break;
        case 'Deli & Prepared Foods':
        document.getElementById("deli_prepared").innerHTML += `<br> ${item}: ${amount}`;
        break;
        case 'Other':
        document.getElementById("other").innerHTML += `<br> ${item}: ${amount}`;
        break;
    }

    document.getElementById("groceryItem").value = null;
    document.getElementById("amountOfItem").value = null;

}


