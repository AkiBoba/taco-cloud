$("#save").click(function () {
     let ingredients = getIngredients();
     let name = $("#name").val();
     let taco = createTacoObject(name, ingredients);
     save(taco);
    })

     /*Заполнение полей формы Input в ордере */
 $("#deliveryName").attr("value", "Vova");
 $("#deliveryStreet").attr("value", "Спортивная");
 $("#deliveryCity").attr("value", "Подольск");
 $("#deliveryState").attr("value", "МО");
 $("#deliveryZip").attr("value", "142143");
 $("#ccNumber").attr("value", "2202203260502846");
 $("#ccExpiration").attr("value", "11/23");
 $("#ccCVV").attr("value", "123");

     /*Заполнение полей формы Input в Taco */
 $("#wrapId").attr("checked", "checked");
 $("#proteinId").attr("checked", "checked");
 $("#cheeseId").attr("checked", "checked");
 $("#veggiesId").attr("checked", "checked");
 $("#sauceId").attr("checked", "checked");
 $("#name").attr("value", "Junior");

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
     console.log(listOfIngredients)
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
         url: '/orderadd',
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
                 alert("Name must be at least 5 characters long. \n You must choose at least 1 ingredient");
             }
         }
     });
 }