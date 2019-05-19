import Card from "@material-ui/core/Card";
import PropTypes from 'prop-types';
import React, { Component } from 'react';
import SinglePost from "../posts/single-post/SinglePost";
import css from "./single-group.module.scss";
import SingleComment from "../comment/single-comment/SingleComment";
import CommentForm from "../comment/CommentForm";


class SingleGroup extends Component {
    static propTypes = {
        currentGroup: PropTypes.shape(),
        posts: PropTypes.arrayOf(PropTypes.shape),
        comments: PropTypes.arrayOf(PropTypes.shape),
    };

    render() {
        const { currentGroup, posts, comments } = this.props;

        const postList = <ul className={css.posts}>
            {posts.map(post => (
                <li key={post.id}>
                    <SinglePost post={post}/>

                    <CommentForm></CommentForm>

                </li>
            ))}
        </ul>;

        const commentList = <ul>
            {comments.map(comment => (
                <li key={comment.id}>
                    <SingleComment comment={comment}/>
                </li>
            ))}
        </ul>

        const noPostsInfo = <h2 className={css.noPostsAvailable}>Brak postów w grupie :(</h2>;
        return (
            <>
                <h1 className={css.groupNameInfo}>
                    {currentGroup ? currentGroup.name : "Nie wybrano grupy"}
                </h1>

                {currentGroup ? (posts && posts.length ? postList : noPostsInfo) : ""}

            </>
        );
    }
}

export default SingleGroup;
