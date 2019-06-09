import { withRouter } from "react-router-dom";
import React, { Component } from "react";
import Card from "@material-ui/core/Card";
import Chart from "./Chart";
import css from "./charts.module.scss";

class Charts extends Component {
    constructor(){
        super();
        this.state = {
            chartData:{}
        };
    }

    componentWillMount() {
        this.getChartData();
    }

    getChartData(){
        this.setState({
            chartData:{
                labels: ['Styczeń', 'Luty', 'Marzec', 'Kwiecień', 'Maj', 'Czerwiec', 'Lipiec', 'Spierpień', 'Wrzesień', 'Październik', 'Listopad', 'Grudzień'],
                datasets: [{
                    label: 'Posty',
                    data:[
                        41, 18, 15, 41, 28, 45, 31, 38, 25, 41, 58, 15,
                    ],
                    backgroundColor:[
                        'rgba(255, 99, 132, 0.8)',
                        'rgba(255, 99, 132, 0.8)',
                        'rgba(255, 99, 132, 0.8)',
                        'rgba(255, 99, 132, 0.8)',
                        'rgba(255, 99, 132, 0.8)',
                        'rgba(255, 99, 132, 0.8)',
                        'rgba(255, 99, 132, 0.8)',
                        'rgba(255, 99, 132, 0.8)',
                        'rgba(255, 99, 132, 0.8)',
                        'rgba(255, 99, 132, 0.8)',
                        'rgba(255, 99, 132, 0.8)',
                        'rgba(255, 99, 132, 0.8)'
                    ]
                },{
                    label: 'Komentarze',
                    data:[
                        21, 28, 45, 11, 18, 25, 21, 18, 35, 11, 48, 55,
                    ],
                    backgroundColor:[
                        'rgba(54, 162, 235, 0.8)',
                        'rgba(54, 162, 235, 0.8)',
                        'rgba(54, 162, 235, 0.8)',
                        'rgba(54, 162, 235, 0.8)',
                        'rgba(54, 162, 235, 0.8)',
                        'rgba(54, 162, 235, 0.8)',
                        'rgba(54, 162, 235, 0.8)',
                        'rgba(54, 162, 235, 0.8)',
                        'rgba(54, 162, 235, 0.8)',
                        'rgba(54, 162, 235, 0.8)',
                        'rgba(54, 162, 235, 0.8)',
                        'rgba(54, 162, 235, 0.8)'
                    ]
                },{
                    label: 'Pliki',
                    data:[
                        11, 8, 5, 1, 13, 6, 7, 2, 11, 9, 6, 3,
                    ],
                    backgroundColor:[
                        'rgba(255, 206, 86, 0.8)',
                        'rgba(255, 206, 86, 0.8)',
                        'rgba(255, 206, 86, 0.8))',
                        'rgba(255, 206, 86, 0.8)',
                        'rgba(255, 206, 86, 0.8)',
                        'rgba(255, 206, 86, 0.8)',
                        'rgba(255, 206, 86, 0.8)',
                        'rgba(255, 206, 86, 0.8)',
                        'rgba(255, 206, 86, 0.8)',
                        'rgba(255, 206, 86, 0.8)',
                        'rgba(255, 206, 86, 0.8)',
                        'rgba(255, 206, 86, 0.8)'
                    ]
                }]
            }
        });
    }

    render() {
        return (
            <Card className={css.chartsWrapper}>
                <div className={css.chart}>
                    <Chart chartData={this.state.chartData}/>
                </div>

            </Card>
        )
    }

}

export default withRouter(Charts);
