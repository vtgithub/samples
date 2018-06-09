import React, { Component } from 'react';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import Avatar from '@material-ui/core/Avatar';
import ImageIcon from '@material-ui/icons/Image';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';

class RosterMaterial extends Component{
    constructor(props){
        super(props);
    }

    render(){
        return (
                <ListItem>
                    <Avatar>
                        <ImageIcon />
                    </Avatar>
                    <ListItemText primary={this.props.value1} secondary={this.props.value2}/>
                </ListItem>
        );

    }
}

export default RosterMaterial;