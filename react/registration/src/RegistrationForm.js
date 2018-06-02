import React, { Component } from 'react';
import RegistrationFormService from './RegistrationFormService.js';
// import {Autobind} from 'es-decorators';

class RegistrationForm extends Component {

    constructor(props){
        super(props);
        this.service = new RegistrationFormService();
        this.service.getIpInfo(function (response) {
            console.log(response);
        });
        this.state={};
    }

    handleChange = (e) => {
        var data = {[e.target.name]:e.target.value};
        this.setState(data);
    }

    render(){
        return (
            <div>
                <label> first name: </label>
                <input type="Text"  placeholder="firstName" name="firstName" onChange={this.handleChange}/>
                <br/>
                <label> last name: </label>
                <input type="Text" placeholder="lastName" name="lastName" onChange={this.handleChange}/>
                <br/>
                <button onClick={()=>this.service.postInfo(this.state)}>submit</button>
            </div>
        );
    }
}

export default RegistrationForm