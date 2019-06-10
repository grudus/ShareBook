import React, { Component } from "react";
import { Bar } from 'react-chartjs-2';

class Chart extends Component {

    state = {
        displayTitle: true,
        displayLegend: true,
        legendPosition: 'right'
    };

    render() {
        return (
            <div className="chart">
                <Bar
                    data={this.props.chartData}
                    width={490}
                    height={200}
                    options={{
                        title: {
                            display: this.state.displayTitle,
                            text: 'Twoja aktywność na portalu',
                            fontSize: 25
                        },
                        legend: {
                            display: this.state.displayLegend,
                            position: this.state.legendPosition
                        },
                        ...this.props.chartData.options
                    }}
                />
            </div>
        )
    }
}

export default Chart;
