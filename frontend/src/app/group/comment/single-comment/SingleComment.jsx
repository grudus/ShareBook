import Avatar from "@material-ui/core/Avatar";
import Card from "@material-ui/core/Card";
import { formatRelative } from 'date-fns'
import * as PropTypes from "prop-types";
import React from 'react';
import LetterAvatar from "../../LetterAvatar";
import css from './single-comment.scss';

const SingleComment = ({ comment }) => {
    const { firstName, lastName } = comment.createdBy;
    const readableDate = formatRelative(new Date(comment.createdAt), new Date());
    const initials = (firstName[0] + lastName[0]).toLocaleUpperCase();

    const avatar = comment.createdBy.avatarUrl
        ? <Avatar alt="avatar"
                  src={comment.createdBy.avatarUrl}
                  className={css.imageAvatar}
        />
        : <LetterAvatar text={initials}/>;

    return (
        <Card>
            <article>
                <div>
                    {avatar}
                    <div >
                        <p >{`${firstName} ${lastName}`}</p>
                        <p >{readableDate}</p>
                    </div>
                </div>
                <p >{comment.text}</p>
            </article>
        </Card>
    );
};


SingleComment.propTypes = {
    comment: PropTypes.shape({
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
    }).isRequired,
};


export default SingleComment;