import Avatar from "@material-ui/core/Avatar";
import Card from "@material-ui/core/Card";
import { formatRelative } from 'date-fns'
import * as PropTypes from "prop-types";
import React from 'react';
import LetterAvatar from "../../LetterAvatar";
import css from './single-comment.scss';

const SingleComment = ({ comment }) => {
    const { firstName, lastName } = comment.createdBy;
    const date = new Date(comment.cratedAt);
    const readableDate = formatRelative(date, new Date());
    const initials = (firstName[0] + lastName[0]).toLocaleUpperCase();

    const avatar = comment.createdBy.avatarUrl
        ? <Avatar alt="avatar"
                  src={comment.createdBy.avatarUrl}
                  className={css.imageAvatar}
        />
        : <LetterAvatar text={initials}/>;

    return (
        <Card className="commentWrapper">
            <article className="comment">
                <div className="commentHeaderWrapper">
                    {avatar}
                    <div className="commentCreatedInfo">
                        <p className="createdByHeader">{`${firstName} ${lastName}`}</p>
                        <p className="createdAtHeader">{readableDate}</p>
                    </div>
                </div>
                <p className="textComment">{comment.text}</p>
            </article>
        </Card>
    );
};


SingleComment.propTypes = {
    comment: PropTypes.shape({
        id: PropTypes.number.isRequired,
        text: PropTypes.string.isRequired,
        cratatedAt: PropTypes.string.isRequired,
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