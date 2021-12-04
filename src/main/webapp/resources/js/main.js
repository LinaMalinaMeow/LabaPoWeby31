document.addEventListener("DOMContentLoaded", function () {
    $("[id='newEmployeeForm:r2']").click()
});
let wrongFieldX = document.getElementById("wrong_field_X");
let wrongFieldY = document.getElementById("wrong_field_Y");
let wrongFieldR = document.getElementById("wrong_field_R");
let wrongFields = document.getElementById("wrong_fields");
let xVal;
let yVal;
let canvasX;
let canvasY;
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
    if ((checkX(x) && checkY(y) && checkR())) {
        canvasX = x
        canvasY = y
        $('.y').val(y);
        $('.hidden_x input[type=hidden]').val(x);
        $('.hidden_r input[type=hidden]').val(rVal);
        $(".hidden-submit").click();
    }
}

$('.input_form_control_buttons_button_submit').on('click', function (event) {
    yVal = $('.y').val()
    if (!checkY() || !checkX() || !checkR()) {
        event.preventDefault()
    } else {
        drawShoot(xVal, yVal, rVal)
        canvasY = yVal
        canvasX = xVal
        wrongFieldX.textContent = ""
        wrongFieldY.textContent = ""
        wrongFieldR.textContent = ""
        $('.hidden_x input[type=hidden]').val(xVal);
        $('.hidden_r input[type=hidden]').val(rVal);
        $(".hidden-submit").click();
    }
});

$('.hidden-submit').on('click', function (event) {
    if (!checkY(canvasY) || !checkX(canvasX) || !checkR()) {
        event.preventDefault()
    } else {
        drawShoot(canvasX, canvasY, rVal)
        wrongFieldX.textContent = ""
        wrongFieldY.textContent = ""
        wrongFieldR.textContent = ""
    }
});



$('.clear').on('click', function (event) {
    clearCanvas();
    drawWithoutPoints();
    /*drawCanvas();*/
});

function checkX(x=xVal) {
    if(!x) {
        wrongFieldX.textContent = "Поле X должно быть заполнено";
        return false
    }
    if (!(x && !isNaN(x))) {
        wrongFieldX.textContent = "X должен быть числом!";
        return false;
    }
    if(!(x >= -5 && x <= 3)) {
        wrongFieldX.textContent = "X должен принадлежать промежутку: [-5; 3]!";
        return false
    }
    return true
}

function checkR(r=rVal) {
    if(!r) {
        wrongFieldR.textContent = "Поле R должно быть заполнено";
        return false
    }
    if (!(r && !isNaN(r))) {
        wrongFieldR.textContent = "R должен быть числом!";
        return false;
    }
    if(!(r >= 1 && r <= 3)) {
        wrongFieldR.textContent = "R должен принадлежать промежутку: [1; 3]!";
        return false
    }
    return true
}

function checkY(y=yVal) {
    if (y === "") {
        wrongFieldY.textContent = "Поле Y должно быть заполнено";
        return false;
    }
    y = y.replace(",", ".")
    if (!(y && !isNaN(y))) {
        wrongFieldY.textContent = "Y должен быть числом!";
        return false;
    }
    if (!((y > -5) && (y < 3))) {
        wrongFieldY.textContent = "Y должен принадлежать промежутку: (-5; 3)!";
        return false;
    }
    return true;
}

function isValid(x, y, r) {
    return (x >= -5 && x <= 3) && (y > -5 && y < 3) && (r >= 1 && r <= 3);
}