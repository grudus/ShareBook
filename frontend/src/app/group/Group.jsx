import React, { Component } from 'react';
import AddGroupDialog from "./AddGroupDialog";
import * as GroupApi from './GroupApi'
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import UserGroupList from "./UserGroupList";


class Group extends Component {

    state = {
        groups: [],
        showDialog: false
    };


    async componentDidMount() {
        const groups = await GroupApi.getAllGroups();
        this.setState({ groups })
    }


    addGroup = async (name) => {
        const { id } = await GroupApi.addGroup(name);
        this.setState(state => ({
            ...state,
            groups: [...state.groups, { id, name }]
        }));
    };

    showDialog = () => {
        this.setState({ showDialog: true })
    };

    hideDialog = () => {
        this.setState({ showDialog: false });
    };

    render() {
        return (
            <div>
                Grupy użytkownika:
                <UserGroupList groups={this.state.groups}/>

                <AddGroupDialog
                    open={this.state.showDialog}
                    onClose={this.hideDialog}
                    onSubmit={this.addGroup}
                />


                <Fab color={"primary"} onClick={this.showDialog}>
                    <AddIcon/>
                </Fab>
            </div>

        );
    }
}


export default Group;
