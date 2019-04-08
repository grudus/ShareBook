import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import AddGroupDialog from "./AddGroupDialog";
import "./group.module.scss";
import css from './group.module.scss';
import * as GroupApi from './GroupApi';
import SingleGroup from "./single/SingleGroup";
import UserGroupList from "./UserGroupList";

class Group extends Component {

    state = {
        groups: [],
        showDialog: false,
        currentGroup: null,
    };


    // noinspection JSCheckFunctionSignatures
    async componentDidMount() {
        const groups = await GroupApi.getAllGroups();
        this.setState({ groups });
        this.changeCurrentGroup();
    }

    // noinspection JSCheckFunctionSignatures
    componentDidUpdate(prevProps) {
        if (this.props.location !== prevProps.location) {
            this.changeCurrentGroup();
        }
    }


    changeCurrentGroup = () => {
        const currentGroupId = this.props.match.params.id;
        const groups = this.state.groups;
        const currentGroup = currentGroupId === null
            ? null
            : groups.find(({ id }) => id === parseInt(currentGroupId, 10));

        this.setState({ currentGroup })

    };


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
        const { currentGroup, groups, showDialog } = this.state;

        return (
            <div className={css.mainPageWrapper}>
                <UserGroupList groups={groups}/>

                <SingleGroup currentGroup={currentGroup}/>

                <AddGroupDialog
                    open={showDialog}
                    onClose={this.hideDialog}
                    onSubmit={this.addGroup}
                />


                <div className={css.addGroupFab}>
                    <Fab
                        color={"primary"} onClick={this.showDialog}>
                        <AddIcon/>
                    </Fab>
                </div>

            </div>
        );
    }
}


export default withRouter(Group);
