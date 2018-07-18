import './Messenger.css'
import React, { Component } from 'react';
import Grid from '@material-ui/core/Grid';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import OnlineMessageService from './OnlineMessageService'
import List from '@material-ui/core/List';
import Divider from '@material-ui/core/Divider';
import RosterMaterial from "./RosterMaterial"
import AccountCircle from '@material-ui/icons/Send';


class MessengerMaterial extends Component{
    constructor(props){
        super(props);
        this.onlineMessageService = new OnlineMessageService();
        this.state={Roster:[
            {title:"title1", description:"description1", id:"user1"},
            {title:"title2", description:"description2", id:"user2"},
            {title:"title3", description:"description3", id:"user3"},
        ]};
        // this.onlineMessageService.getRos terList ("test", (rosterList) => {
        //     this.setState({Roster:rosterList});
        // });

        // console.log(rosterList);
    }

    render(){

        return (

            <Grid container justify="center" >
                <Grid item xs={3}>
                    <Card  className="Roster">
                        <CardContent>
                            {this.renderRosters()}
                        </CardContent>
                    </Card>
                </Grid>
                <Grid item xs={9}>
                    <Card  className="ConversationArea">
                        <CardContent></CardContent>
                        <CardActions>
                            <Grid container spacing={8} alignItems="flex-end">
                                <Grid item xs={11}>
                                    <TextField fullWidth
                                               name="message"
                                               placeholder="type here ..."
                                               onChange={this.handleChange}/>
                                </Grid>
                                <Grid item xs={1} onClick={()=>this.handleSendClick()}>
                                    <AccountCircle />
                                </Grid>
                            </Grid>
                        </CardActions>
                    </Card>
                </Grid>
            </Grid>

        );
    }

    renderRosters(){
        const listItems = this.state.Roster.map((rosterMember) =>
            <div onClick={()=>this.handleRosterClick(rosterMember)}>
                <RosterMaterial value1={rosterMember.title} value2={rosterMember.description}/>
                <Divider inset/>
            </div>
        );
        return(
            <list>
                {listItems}
            </list>
        );

    }

    handleRosterClick(roster){
        alert('get roster chat info by:'+roster.id);
        //todo call history
    }

    handleSendClick(){
        alert(this.state.message)
    }

    handleChange = (e) => {
        this.setState(
            {[e.target.name]:e.target.value}
        );
    }

}

export default MessengerMaterial;