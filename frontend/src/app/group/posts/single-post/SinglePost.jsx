import Avatar from "@material-ui/core/Avatar";
import Card from "@material-ui/core/Card";
import { formatRelative } from 'date-fns'
import * as PropTypes from "prop-types";
import React from 'react';
import LetterAvatar from "../../LetterAvatar";
import css from './single-post.module.scss';
import CommentForm from "../../comment/CommentForm";
import SingleComment from "../../comment/single-comment/SingleComment";
import * as CommentApi from "../../comment/CommentApi";


const SinglePost = ({ post, comments}) => {
    const { firstName, lastName } = post.createdBy;
    const readableDate = formatRelative(new Date(post.createdAt), new Date());
    const initials = (firstName[0] + lastName[0]).toLocaleUpperCase();

    const avatar = post.createdBy.avatarUrl
        ? <Avatar alt="avatar"
                  src={post.createdBy.avatarUrl}
                  className={css.imageAvatar}
        />
        : <LetterAvatar text={initials}/>;


    const commentList = <ul>
        {comments.map(comment => (
            <li key={comment.id}>
                <SingleComment comment={comment}/>
            </li>
        ))}
    </ul>;

     const addComment = async (commentText) => {
        await CommentApi.addComment(post.groupId, post.postId ,commentText);
         this.props.afterCommentAdded();
    };


    return (
        <Card className={css.postWrapper}>
            <article className={css.post}>
                <div className={css.postHeaderWrapper}>
                    {avatar}
                    <div className={css.postCreatedInfo}>
                        <p className={css.createdByHeader}>{`${firstName} ${lastName}`}</p>
                        <p className={css.createdAtHeader}>{readableDate}</p>
                    </div>
                </div>
                <p className={css.postText}>{post.text}</p>
                {commentList}
                <div className={css.commentForm}>
                <CommentForm actionWhenAddComment={(text) => addComment(text)}/>
                </div>
            </article>
        </Card>
    );
};


SinglePost.propTypes = {
    afterCommentAdded: PropTypes.func.isRequired,
    post: PropTypes.shape({
        text: PropTypes.string.isRequired,
        groupId: PropTypes.number.isRequired,
        postId: PropTypes.number.isRequired,
        createdBy: PropTypes.shape({
            email: PropTypes.string,
            firstName: PropTypes.string,
            lastName: PropTypes.string,
            avatarUrl: PropTypes.string,
        }).isRequired,
        createdAt: PropTypes.string.isRequired,
        comments: PropTypes.shape({
            id: PropTypes.number.isRequired,
            text: PropTypes.string.isRequired,
            createdAt: PropTypes.string.isRequired,
            createdBy: PropTypes.shape({
                id: PropTypes.number.isRequired,
                email: PropTypes.string,
                firstName: PropTypes.string,
                lastName: PropTypes.string,
                avatarUrl: PropTypes.string,
            }).isRequired,
            postId: PropTypes.number.isRequired,
        }),
    }).isRequired,
};


export default SinglePost;
