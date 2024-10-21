function confirmLogout() {
    if (confirm("Bạn chắc chắn muốn đăng xuất?")) {
        window.location.href = "/login";
    }
}


function confirmSubmission() {
    alert("Thông tin đã được gửi về công ty, chúng tôi sẽ liên hệ lại với bạn.");
    setTimeout(function() {
        window.location.href = "/home";
    }, 2000);

    return true;
}
