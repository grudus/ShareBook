import FormControl from "@material-ui/core/FormControl";
import MenuItem from "@material-ui/core/MenuItem";
import Select from "@material-ui/core/Select";
import React, { useState } from 'react';


const YearSelect = ({ years, onSelect }) => {
    const [selected, setSelected] = useState(years[0]);
    const _onSelect = (e) => {
        const { value } = e.target;
        setSelected(value);
        onSelect(value)
    };

    return (
        <FormControl>
            <Select
                value={selected}
                onChange={_onSelect}
                displayEmpty
            >
                {years.map(year => (
                    <MenuItem value={year}>{year}</MenuItem>
                ))}
            </Select>
        </FormControl>

    );
};


export default YearSelect;
