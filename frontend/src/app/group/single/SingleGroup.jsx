import { Card, CardMedia } from "@material-ui/core";
import React, { Component } from 'react';
import css from "./single-group.module.scss";
import PropTypes from 'prop-types';


class SingleGroup extends Component {
    static propTypes = {
        currentGroup: PropTypes.shape().isRequired,
    };

    render() {
        const { currentGroup } = this.props;

        return (
            <>
                <Card className={css.Card}>
                    {currentGroup ? "Wybrana grupa to: " + currentGroup.name : "Nie wybrano grupy"}
                </Card>
            </>
        );
    }
}

export default SingleGroup;
