 $("#save").click(function () {
     let ingredients = getIngredients();
     let name = $("#name").val();
     let taco = createTacoObject(name, ingredients);
     console.log(taco.name)
     save(taco);
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
     console.log(listOfIngredients[0].id, listOfIngredients[1].name);
     return listOfIngredients;
 }

 function createTacoObject(name, ingredients) {
     return {
         name: name,
         ingredients: ingredients
     }
 }

 function save(taco) {
     console.log("save")
     $.ajax({
         url: '/ordersadd',
         type: 'post',
         data: JSON.stringify(taco),
         headers: {
             'Accept': 'application/json',
             'Content-Type': 'application/json'
         },
         complete: function(data){
             console.log(data.status);
             $(".new-taco").removeAttr("hidden");
             $(".item").text(taco.name);
             $(".new-order").removeAttr("hidden");
         }
     });
 }