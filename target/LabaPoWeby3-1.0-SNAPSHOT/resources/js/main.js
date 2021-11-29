document.addEventListener("DOMContentLoaded", function () {
    $('.r2').prop('checked', true);
    rVal = 2;
    console.log(getR())
    drawCanvas()
});
let wrongFieldX = document.getElementById("wrong_field_X");
let wrongFieldY = document.getElementById("wrong_field_Y");
let wrongFieldR = document.getElementById("wrong_field_R");
let xVal;
let yVal;
let rVal;
$('.x').on('click', function (event) {
    xVal = $(this).val();
    $(this).addClass('clicked');
    $('.x').not(this).removeClass('clicked');
    $('.hidden_x input[type=hidden]').val(xVal);
});

$('.r').on('click', function (event) {
    rVal = $(this).attr('id')
    rVal = rVal.slice(17, rVal.length)
    if (rVal.length === 2) {
        rVal = rVal[0] + '.' + rVal[1]
    }
    clearCanvas()
    drawCanvas()
    $(this).addClass('clicked');
    $('.r').not(this).removeClass('clicked');
    $('.r').not(this).prop('checked', false);
    $('.hidden_r input[type=hidden]').val(rVal);
});

function getR() {
    return rVal;
}

function clickOnChart(canvas, event) {
    let rect = canvas.getBoundingClientRect()
    let width = canvas.width;
    let height = canvas.height;
    let x = (event.clientX - rect.left - width / 2) / step;
    let y = (height / 2 - event.clientY + rect.top) / step;
    x = x.toFixed(2).replace(".00", "");
    y = y.toFixed(2).replace(".00", "");
    if (isValid(x, y, rVal)) {
        xVal = x
        yVal = y
        $('.y').val(yVal);
        $('.hidden_x input[type=hidden]').val(xVal);
        $('.hidden_r input[type=hidden]').val(rVal);
        $(".submit").click();

    }
}

$('.input_form_control_buttons_button_submit').on('click', function (event) {

    drawShoot(xVal, $('.y').val(), rVal)
    wrongFieldX.textContent = ""
    wrongFieldY.textContent = ""
    wrongFieldR.textContent = ""
    if (!checkY()) {
        console.log("ya tyt")
        event.preventDefault()
    }
});

$('.clear').on('click', function (event) {

    clearCanvas();
    drawCanvas();
});

function checkY() {
    yVal = $('.y').val();
    console.log("y=" + yVal)
    if (yVal === "") {
        wrongFieldY.textContent = "Поле Y должно быть заполнено";
        return false;
    }
    yVal = yVal.replace(",", ".")
    if (!(yVal && !isNaN(yVal))) {
        wrongFieldY.textContent = "Y должен быть числом!";
        return false;
    }
    if (!((yVal >= -5) && (yVal <= 3))) {
        wrongFieldY.textContent = "Y должен принадлежать промежутку: (-5; 3)!";
        return false;
    }
    return true;
}

function isValid(x, y, r) {
    return (x >= -5 && y <= 3) && (y >= -5 && y <= 3) && (r >= 1 && r <= 3);
}