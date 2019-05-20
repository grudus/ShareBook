import Card from "@material-ui/core/Card";
import PropTypes from 'prop-types';
import React, { Component } from 'react';
import SinglePost from "../posts/single-post/SinglePost";
import css from "./single-group.module.scss";


class SingleGroup extends Component {
    static propTypes = {
        currentGroup: PropTypes.shape(),
        postWithComments: PropTypes.arrayOf(PropTypes.shape)
    };

    render() {
        const { currentGroup, postWithComments} = this.props;


        const postList = <ul className={css.posts}>
            {postWithComments.map(({post, comments}) => (
                <li key={post.id}>
                    <SinglePost post={post} comments={comments}/>
                </li>
            ))}
        </ul>;

        const noPostsInfo = <h2 className={css.noPostsAvailable}>Brak postów w grupie :(</h2>;
        return (
            <>
                <h1 className={css.groupNameInfo}>
                    {currentGroup ? currentGroup.name : "Nie wybrano grupy"}
                </h1>

                {currentGroup ? (postWithComments && postWithComments.length ? postList : noPostsInfo) : ""}

            </>
        );
    }
}

export default SingleGroup;
