$(document).ready(function() {
    })

function getIngredients() {
    let listOfIngredients = [];
    listOfIngredients.push({
        id: $("#wrapId").val(),
        name: $("#wrapName").text(),
        type: 'WRAP'
    });
    listOfIngredients.push({
        id: $("#proteinId").val(),
        name: $("#proteinName").text(),
        type: 'PROTEIN'
    });
    listOfIngredients.push({
        id: $("#veggiesId").val(),
        name: $("#veggiesName").text(),
        type: 'VEGGIES'
    });
    listOfIngredients.push({
        id: $("#cheeseId").val(),
        name: $("#cheeseName").text(),
        type: 'CHEESE'
    });
    listOfIngredients.push({
        id: $("#sauceId").val(),
        name: $("#sauceName").text(),
        type: 'SAUCE'
    });
    console.log(listOfIngredients);
    return listOfIngredients;

}

function createTacoObject(name, ingredients) {
    return {
        name: name,
        ingredients: ingredients
        }
    }

function save(taco) {
    console.log("save");
    $.ajax({
        url: '/design',
        type: 'post',
        data: {
            taco: taco
        }
    });

$(function () {
    $("#save").click(function () {
            let ingredients = getIngredients();
            let name = $("#name").val();
            let taco = createTacoObject(name, ingredients);
        console.log(taco.name);
        console.log("save");
            save(taco);
        })
    });

}