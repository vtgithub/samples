class RegistrationFormService {
    postInfo(registrationData) {
        console.log(registrationData);
    }

//     fetch('https://mywebsite.com/endpoint/', {
//     method: 'POST',
//     headers: {
//         'Accept': 'application/json',
//         'Content-Type': 'application/json',
//     },
//     body: JSON.stringify({
//     firstParam: 'yourValue',
//     secondParam: 'yourOtherValue',
// })
// })

    getIpInfo(callback) {
        fetch('https://ipapi.co/5.53.63.200/json/')
            .then((response) => response.json())
            .then((responseJson) => {
                callback(responseJson);
                // this.setState({
                //     isLoading: false,
                //     dataSource: responseJson.movies,
                // }, function(){
                //
                // });
            })
            .catch((error) => {
                console.error(error);
            });
    }
}

export default RegistrationFormService