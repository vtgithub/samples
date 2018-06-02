class AjaxTest {
    getIpData(){
        fetch("https://ipapi.co/5.53.63.200/json/")
            .then(res => res.json())
            .then(
                (result) => {
                    console.log(result);
                    // alert(result);
                    // this.setData({d:result.region});
                },
                (error) => {
                    alert("error");
                }
            )
    }
}

export default AjaxTest