 $("#save").click(function () {
     let ingredients = getIngredients();
     let name = $("#name").val();
     let taco = createTacoObject(name, ingredients);
     // console.log(taco.name)
     save(taco);
    })

 function getIngredients() {
     let listOfIngredients = [];
     listOfIngredients.push({
         id: $("#wrapId:checked").val(),
         type: 'WRAP'
     });
     listOfIngredients.push({
         id: $("#proteinId:checked").val(),
         type: 'PROTEIN'
     });
     listOfIngredients.push({
         id: $("#veggiesId:checked").val(),
         type: 'VEGGIES'
     });
     listOfIngredients.push({
         id: $("#cheeseId:checked").val(),
         type: 'CHEESE'
     });
     listOfIngredients.push({
         id: $("#sauceId:checked").val(),
         type: 'SAUCE'
     });
     // console.log(listOfIngredients[0].id, listOfIngredients[1].name);
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
             if (data.status == 200) {
                 $(".new-taco").removeAttr("hidden");
                 $(".item").text(taco.name);
                 $(".new-order").removeAttr("hidden");
             }
             else {
                 console.log("error");
                 // $.ajax({
                 //     url: '/design',
                 //     type: 'get'
                 // });
                 $(".validationError").removeAttr("hidden");
             }
         }
     });
 }