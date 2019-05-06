import { Card, CardMedia } from "@material-ui/core";
import React, { Component } from 'react';
import css from "./single-group.module.scss";
import PropTypes from 'prop-types';


class SingleGroup extends Component {
    static propTypes = {
        currentGroup: PropTypes.shape(),
        posts: PropTypes.shape(),
    };

    render() {
        const { currentGroup, posts } = this.props;

        return (
            <>
                <Card className={css.Card}>
                    {currentGroup ? "Wybrana grupa to: " + currentGroup.name : "Nie wybrano grupy"}

                    <ul className={css.posts}>
                        {posts.map(post => (
                            <li className={css.singlePost}>
                                <span>{post.text}</span>
                                <span>{post.createdAt}</span>
                                <span>{post.createdBy.email}</span>
                            </li>
                        ))}
                    </ul>
                </Card>
            </>
        );
    }
}

export default SingleGroup;
