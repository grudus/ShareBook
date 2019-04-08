import React from 'react';
import PropTypes from 'prop-types';
import { withStyles } from '@material-ui/core/styles';
import Avatar from '@material-ui/core/Avatar';
import Grid from '@material-ui/core/Grid';
import css from './group.module.scss';

const styles = {
    avatar: {
        margin: 10,

    },

};

function ImageAvatars(props) {
    const { classes } = props;
    return (
        <div className={css.ImageAvatar}>
            <Avatar alt="Remy Sharp" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTa4tG8WJkzRsq8GsHtuxFtcpmXrp89GffQSYAkP-HQscRIR8IC8A" className={classes.avatar} />

        </div>
    );
}

ImageAvatars.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(ImageAvatars);