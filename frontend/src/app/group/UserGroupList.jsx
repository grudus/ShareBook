import React from 'react';
import PropTypes from 'prop-types'


const UserGroupList = ({ groups }) => (
    <ul>
        {groups.map(group => (
            <li key={group.id}>{group.name}</li>
        ))}

    </ul>);


UserGroupList.propTypes = {
    groups: PropTypes.arrayOf(PropTypes.shape({
        name: PropTypes.string,
    })).isRequired
};
export default UserGroupList;
