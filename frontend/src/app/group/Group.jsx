import React, { Component } from 'react';
import { withRouter } from "react-router-dom";
import AddUserToGroup from "../user-group/AddUserToGroup";
import AddMultipleButton from "./add-button/AddMultipleButton";
import AddGroupDialog from "./dialogs/AddGroupDialog";
import "./group.module.scss";
import AddPostDialog from "./dialogs/AddPostDialog";
import css from './group.module.scss';
import * as GroupApi from './GroupApi';
import * as PostApi from './posts/PostApi';
import SingleGroup from "./single/SingleGroup";
import UserGroupList from "./UserGroupList";

class Group extends Component {

    state = {
        groups: [],
        showAddGroupDialog: false,
        showAddPostDialog: false,
        currentGroup: null,
        usersForCurrentGroup: [],
        posts: []
    };


    //noinspection JSCheckFunctionSignatures
    componentDidMount() {
        GroupApi.getAllGroups()
            .then(groups => {
                this.setState({ groups });
                this.changeCurrentGroup();
            });
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

        this.setState({ currentGroup, usersForCurrentGroup: [] });

        if (currentGroupId) {
            GroupApi.findAllUsersForGroup(currentGroupId)
                .then(users => this.setState({ usersForCurrentGroup: users }))

            PostApi.getPostsForGroup(currentGroupId)
                .then(posts => this.setState({ posts }))
        }

    };


    addGroup = async (name, photoUrl) => {
        const { id } = await GroupApi.addGroup(name, photoUrl);
        this.setState(state => ({
            ...state,
            groups: [...state.groups, { id, name, photoUrl }]
        }));
    };

    addPost = async (postText) => {
        await PostApi.addPost(this.state.currentGroup.id, postText);

        PostApi.getPostsForGroup(this.state.currentGroup.id)
            .then(posts => this.setState({ posts }))
    };

    showDialog = (dialog) => {
        this.setState({ [dialog]: true })
    };

    hideDialog = (dialog) => {
        this.setState({ [dialog]: false });
    };

    render() {
        const { currentGroup, groups, showAddGroupDialog, showAddPostDialog, usersForCurrentGroup, posts } = this.state;
        return (
            <div className={css.mainPageWrapper}>
                <div className={css.userGroupWrapper}>
                    <UserGroupList groups={groups}/>
                </div>
                <div className={css.singleGroupWrapper}>
                    <SingleGroup currentGroup={currentGroup} posts={posts}/>
                </div>

                {currentGroup && (
                    <>
                        <div className={css.addUserToGroupWrapper}>
                            <AddUserToGroup groupId={currentGroup.id}/>
                        </div>
                        <div className={css.users}>
                            <div className={css.userList}>
                                <label>Użytkownicy należący do grupy:</label>
                                <br/><br/>
                                {!!usersForCurrentGroup.length &&
                                <ul>
                                    {usersForCurrentGroup.map(user => (
                                        <li key={user.id}>
                                            {user.firstName} {user.lastName}
                                        </li>))}
                                </ul>}
                            </div>
                        </div>
                    </>
                )}

                <AddMultipleButton
                    onAddGroup={() => this.showDialog('showAddGroupDialog')}
                    onAddPost={() => this.showDialog('showAddPostDialog')}
                    addPostAvailable={!!currentGroup}
                />

                <AddGroupDialog
                    open={showAddGroupDialog}
                    onClose={() => this.hideDialog('showAddGroupDialog')}
                    onSubmit={this.addGroup}
                />

                <AddPostDialog
                    open={showAddPostDialog}
                    onClose={() => this.hideDialog('showAddPostDialog')}
                    onSubmit={(text) => this.addPost(text)}
                />

            </div>
        );
    }
}


export default withRouter(Group);
