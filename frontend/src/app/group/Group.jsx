import { Card } from "@material-ui/core";
import Fab from '@material-ui/core/Fab';
import AddIcon from '@material-ui/icons/Add';
import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import TopBar from "../menu/TopBar";
import AddGroupDialog from "./AddGroupDialog";
import "./group.module.scss";
import css from './group.module.scss';
import * as GroupApi from './GroupApi';
import UserGroupList from "./UserGroupList";

class Group extends Component {

    state = {
        groups: [],
        showDialog: false,
        currentGroup: null,
    };


    async componentDidMount() {
        // const groups = await GroupApi.getAllGroups();
        const groups = [
            {
                id: 1,
                name: "Grupa1",
                photoUrl: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTa4tG8WJkzRsq8GsHtuxFtcpmXrp89GffQSYAkP-HQscRIR8IC8A"
            },
            {
                id: 2,
                name: "Grupa2",
                photoUrl: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTa4tG8WJkzRsq8GsHtuxFtcpmXrp89GffQSYAkP-HQscRIR8IC8A"
            },
            {
                id: 3,
                name: "Grupa3",
                photoUrl: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTa4tG8WJkzRsq8GsHtuxFtcpmXrp89GffQSYAkP-HQscRIR8IC8A"
            },
            {
                id: 4,
                name: "Grupa4",
                photoUrl: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTa4tG8WJkzRsq8GsHtuxFtcpmXrp89GffQSYAkP-HQscRIR8IC8A"
            },


        ];

        this.setState({ groups });
        this.changeCurrentGroup();
    }

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
                <div className={css.groups}>
                    Dostępne grupy:
                    <UserGroupList groups={groups}/>
                </div>
                <Card className={css.Card}>
                    {currentGroup ? "Wybrana grupa to: " + currentGroup.name : "Nie wybrano grupy"}
                </Card>
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
