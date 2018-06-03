import React, { Component } from 'react';
import RegistrationFormService from './RegistrationFormService.js';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Grid from '@material-ui/core/Grid';
import Modal from '@material-ui/core/Modal';

class RegistrationFormMaterial extends Component{
    constructor(props){
        super(props);
        this.service = new RegistrationFormService();
        this.service.getIpInfo(function (response) {
            console.log(response);
        });
        this.state={};
    }

    // handleChange = (e) => {
    //     this.setData(
    //             {[e.target.name]:e.target.value}
    //     );
    // }
    handleChange = (e) => {
        this.setState(
            {[e.target.name]:e.target.value}
        );
    }

    render(){
        return (
            <div>
                <Grid container justify="center" spacing={24}>
                    <Grid item xs={12} lg={6} xl={3} >
                        <Card className="Card">
                            <CardContent>
                                <Grid container spacing={24}>
                                    <Grid item xs={12}>
                                        <TextField fullWidth name="firstName" placeholder="firstName" onChange={this.handleChange}/>
                                    </Grid>
                                    <Grid item xs={12}>
                                        <TextField fullWidth name="lastName" placeholder="lastName" onChange={this.handleChange}/>
                                    </Grid>
                                </Grid>
                            </CardContent>
                            <CardActions>
                                <Grid container justify="center">
                                    <Button justify="center" variant="raised" color="primary" onClick={()=>this.handleClick()}>Submit</Button>
                                </Grid>
                            </CardActions>
                        </Card>
                    </Grid>
                </Grid>

            </div>
        );
    }

    handleClick(){
        this.service.postInfo(this.state);
        // this.setState({open:true});
        console.log(this.state.info)

    }

}

export default RegistrationFormMaterial;