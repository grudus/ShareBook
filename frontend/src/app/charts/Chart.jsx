import React, { Component } from "react";
import {Bar, Line, Pie} from 'react-chartjs-2';

class Chart extends Component{
    constructor(props) {
        super(props);
        this.state = {
            chartData:props.chartData
        }
    }

    static defaultProps = {
        displayTitle: true,
        displayLegend: true,
        legendPosition: 'right'
    }

    render(){
        return(
            <div className="chart">
                <Bar
                    data={this.state.chartData}
                    width={490}
                    height={200}
                    options={{
                        title:{
                            display: this.props.displayTitle,
                            text: 'Twoja aktywność na portalu',
                            fontSize: 25
                        },
                        legend:{
                            display:this.props.displayLegend,
                            position:this.props.legendPosition
                        }
                    }}
                />
            </div>
        )
    }
}

export default Chart;