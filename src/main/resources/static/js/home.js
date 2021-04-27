$("#iban-form").submit(function(e) {

    e.preventDefault(); // avoid to execute the actual submit of the form.

    let form = $(this);
    let resultTable = $('#iban-extract-result');

    $.ajax({
        type: "POST",
        url: "/extract",
        data: form.serialize(), // serializes the form's elements.
        success: function(data)
        {
            data.forEach(function(resultRow) {
                var tableRow = `<tr>
                            <td>
                              ${resultRow.code}
                            </td>
                           </tr>`;
                resultTable
                    .append(tableRow);
            });
        }
    });
});