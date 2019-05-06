import Card from "@material-ui/core/Card";
import * as PropTypes from "prop-types";
import React from 'react';
import LetterAvatar from "../../LetterAvatar";
import css from './single-post.module.scss';
import { format, formatDistance, formatRelative, subDays } from 'date-fns'


const SinglePost = ({ post }) => {
    const { firstName, lastName } = post.createdBy;
    const readableDate = formatRelative(new Date(post.createdAt), new Date());
    const initials = (firstName[0] + lastName[0]).toLocaleUpperCase();
    return (
        <Card className={css.postWrapper}>
            <article className={css.post}>
                <div className={css.postHeaderWrapper}>
                    <LetterAvatar text={initials}/>
                    <div className={css.postCreatedInfo}>
                        <p className={css.createdByHeader}>{`${firstName} ${lastName}`}</p>
                        <p className={css.createdAtHeader}>{readableDate}</p>
                    </div>
                </div>
                <p className={css.postText}>{post.text}</p>
            </article>
        </Card>
    );
};


SinglePost.propTypes = {
    post: PropTypes.shape({
        text: PropTypes.string.isRequired,
        createdAt: PropTypes.string,
        createdBy: PropTypes.shape({
            email: PropTypes.string,
            firstName: PropTypes.string,
            lastName: PropTypes.string,
        }).isRequired
    }).isRequired,
};


export default SinglePost;
