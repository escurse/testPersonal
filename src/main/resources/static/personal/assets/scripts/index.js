const $form = document.getElementById('form');
const $div = document.createElement('div');

$form.onsubmit = (e) => {
    e.preventDefault();
    const xhr = new XMLHttpRequest();
    const formData = new FormData();
    formData.append('name', $form['name'].value);
    formData.append('gender', $form['gender'].value);
    formData.append('ssnBirth', $form['first'].value);
    formData.append('ssnKey', $form['last'].value);
    formData.append('region', $form['region'].value);
    xhr.onreadystatechange = () => {
        if (xhr.readyState !== XMLHttpRequest.DONE) {
            return;
        }
        if (xhr.status < 200 || xhr.status >= 300) {
            alert('오류 발생');
            return;
        }
        const response = JSON.parse(xhr.responseText);
        if (response['result'] === "success") {
            $div.innerText = '데이터를 성공적으로 추가하였습니다.'
            $div.style.color = "green";
        } else {
            $div.innerText = '데이터를 추가하지 못하였습니다.'
            $div.style.color = "red";
        }
        $form.append($div);
        $form['name'].value = "";
        $form['gender'].value = "M";
        $form['first'].value = "";
        $form['last'].value = "";
        $form['region'].value = "서울특별시";
    };
    xhr.open('POST', location.href);
    xhr.send(formData);
}
