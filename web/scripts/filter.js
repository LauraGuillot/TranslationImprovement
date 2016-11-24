/*Filtering of the table*/

$(document).ready(function () {

    $('#mytable').DataTable({
         
        initComplete: function () {
            this.api().columns().every(function () {

                var column = this;

                if (column.index() == 2 || column.index() == 3){
                    return;
                }
                var select = $('<select><option value=""></option></select>')
                        .appendTo($(column.footer()).empty())
                        .on('change', function () {
                            var val = $.fn.dataTable.util.escapeRegex(
                                    $(this).val()
                                    );

                            column
                                    .search(val ? '^' + val + '$' : '', true, false)
                                    .draw();
                        });

                column.data().unique().sort().each(function (d, j) {
                    select.append('<option value="' + d + '">' + d + '</option>');
                });
            });
        },
    paging: false,
         bInfo : false,
    });
});