import React, { Component } from 'react';
import AddGroupDialog from "./AddGroupDialog";
import * as GroupApi from './GroupApi'
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import UserGroupList from "./UserGroupList";
import "./group.module.scss";
import css from './group.module.scss';

class Group extends Component {

    state = {
        groups: [],
        showDialog: false
    };


    async componentDidMount() {
        const groups = await GroupApi.getAllGroups();
        this.setState({ groups })
    }


    addGroup = async (name, photoUrl) => {
        const { id } = await GroupApi.addGroup(name, photoUrl);
        this.setState(state => ({
            ...state,
            groups: [...state.groups, { id, name, photoUrl }]
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
            <div className={css.topBar}>
                <label className={css.title}>Sharebook</label>
            </div>
                <div>
                Dostępne grupy:
                <UserGroupList groups={this.state.groups}/>
                </div>
                <div className={css.button}>
                <AddGroupDialog
                    open={this.state.showDialog}
                    onClose={this.hideDialog}
                    onSubmit={this.addGroup}
                />


                <Fab color={"primary"} onClick={this.showDialog}>
                    <AddIcon/>
                </Fab>
                </div>

            </div>
        );
    }
}


export default Group;
