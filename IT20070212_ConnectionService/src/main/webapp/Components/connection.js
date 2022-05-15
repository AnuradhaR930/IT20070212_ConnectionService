$(document).ready(function() {
    if ($('#alertSuccess').text().trim() == "") {
        $('#alertSuccess').hide();
    }

    $('#alertError').hide();
})

// SAVE
$(document).on("click","#btnSave", function(event) {
    // Clear alerts
    $("#alertSuccess").text(""); 
    $("#alertSuccess").hide(); 
    $("#alertError").text(""); 
    $("#alertError").hide();

    // Form validation
    var status = validateItemForm(); 
    if (status != true) 
    { 
        $("#alertError").text(status); 
        $("#alertError").show(); 
        return; 
    } 

    // if hidItemIDSave value is null set as POST else set as PUT
    var type = ($("#hidConnectionIDSave").val() == "") ? "POST" : "PUT";

    // ajax communication
    $.ajax({
        url: "ConnectionAPI",
        type: type,
        data: $("#formConnection").serialize(),
        dataType: "text",
        complete: function(response, status) {
            onItemSaveComplete(response.responseText, status);
        }
    });
});

// after completing save request
function onItemSaveComplete(response, status) {

    if (status == "success") { //if the response status is success
        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() === "success") { //if the json status is success
            //display success alert
            $("#alertSuccess").text("Successfully saved");
            $("#alertSuccess").show();
    
            //load data in json to html
            $("#divConnectionGrid").html(resultSet.data);

        } else if (resultSet.status.trim() === "error") { //if the json status is error
            //display error alert
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") { 
        //if the response status is error
        $("#alertError").text("Error while saving");
        $("#alertError").show();
    } else { 
        //if an unknown error occurred
        $("#alertError").text("Unknown error occurred while saving");
        $("#alertError").show();
    } 

    //resetting the form
    $("#hidConnectionIDSave").val("");
    $("#formConnection")[0].reset();
}

// UPDATE


//to identify the update button we didn't use an id we used a class
$(document).on("click", ".btnUpdate", function(event) 
{ 

	
    //get item id from the data-connectionId attribute in update button
    $("#hidConnectionIDSave").val($(this).data('connectionId')); 
    //get data from <td> element
    $("#accountNo").val($(this).closest("tr").find('td:eq(0)').text()); 
    $("#connectionName").val($(this).closest("tr").find('td:eq(1)').text()); 
    $("#serviceId").val($(this).closest("tr").find('td:eq(2)').text()); 
    $("#customerId").val($(this).closest("tr").find('td:eq(3)').text()); 
    $("#connectionDate").val($(this).closest("tr").find('td:eq(4)').text()); 
    $("#connectionStatus").val($(this).closest("tr").find('td:eq(5)').text()); 
     
}); 

// DELETE
$(document).on("click",".btnRemove", function(event) {
     
    // ajax communication
    $.ajax({
        url: "ConnectionAPI",
        type: "DELETE",
       // data: "connectionId=" + $(this).data("connectionId"),
        data: "connectionId=" + $(this).data('connectionId'), 
        dataType: "text",
        
        complete: function(response, status) {
            onItemDeleteComplete(response.responseText, status);
        }
    });
});

// after completing delete request
function onItemDeleteComplete(response, status) {

    if (status == "success") { //if the response status is success
        var resultSet = JSON.parse(response);

        if (resultSet.status.trim() === "success") { //if the json status is success
            //display success alert
            $("#alertSuccess").text("Successfully deleted");
            $("#alertSuccess").show();
    
            //load data in json to html
            $("#divConnectionGrid").html(resultSet.data);

        } else if (resultSet.status.trim() === "error") { //if the json status is error
            //display error alert
            $("#alertError").text(resultSet.data);
            $("#alertError").show();
        }
    } else if (status == "error") { 
        //if the response status is error
        $("#alertError").text("Error while deleting");
        $("#alertError").show();
    } else { 
        //if an unknown error occurred
        $("#alertError").text("Unknown error occurred while deleting");
        $("#alertError").show();
    } 
}

// VALIDATION
function validateItemForm() { 
    
    // Account No
    if ($("#accountNo").val().trim() == "") 
    { 
        return "Insert Account No."; 
    } 
    
    
    // is numerical value 
    var tmpPrice = $("#accountNo").val().trim(); 
    if (!$.isNumeric(tmpPrice)) 
    { 
        return "Insert a numerical value for Account No."; 
    } 
    
    
    
    // Connection Name 
    if ($("#connectionName").val().trim() == "") 
    { 
        return "Insert Connection Name."; 
    } 
    
    return true; 
} 
 