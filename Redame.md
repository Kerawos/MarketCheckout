->To run application:
1. There is already prepared template "index.html located in "../templates/index.html". You have to just run
'MarketCheckoutApplication' and type in your default browser "http://localhost:8080/"

->To prepare own template or use already defined template:
1. Make sure your html contain at least form to manage 'UserRequestModel';
2. Key inputs are 'item name' and 'item quantity";
3. Your html should support 'post' method, also contain message called 'result' to display cumulative results;
4. You have to update 'urlGetUserPriceRequest' and 'templateUserPriceRequest' or replace it in proper places 
in Get/Post mapping. In this example those temp string are temporary and should disappear in true project;
5. you can always create own test template to run application and check application. To do this create .html template
in templates folder. Your template have to contain at least:
<form method="post" th:action="@{/}" th:object="${userRequestModel}">
<input type="text" th:field="*{itemName}">
<input type="text" th:field="*{quantity}">
<input type="submit" value="Scan">
<p th:if="${result}!=null" th:text="${result}"></p>
6. Next change is get rid of temp strings mentioned above or update it respectively "/"(define your Url path to display
template by updating 'urlGetUserPriceRequest' string) and "index" ('templateUserPriceRequest' your template name).


->Some explaination:
1. 'UserInputModel' class is a entity for holding users input to send requests;
2. 'ItemModelDaoImpl' contain simple logic as scan item name and calculate price;
3. There is no Database, in this example Itemlist are stored in simple array;
4. There are some unit separate tests and also integration tests.
5. In this exercise price are holding by 'int' instead of e.g. BigDecimals to simplify calculations. 