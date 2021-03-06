import React from "react";
import {withStyles} from '@material-ui/core/styles';

import DonationTable from "./DonationTable";


const styles = theme => ({
  button: {
    position: 'fixed',
    right: 20,
    bottom: 20,
    margin: theme.spacing.unit,
  }
});

class DonationFeature extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      donations: props.donations || []
    }

    this.rowClick = (user) => {
      this.setState({user})
    }

    fetch('/api/donations')
      .then(res => res.json())
      .then(json => {
        this.setState({donations: json});
      });

  }

  render() {

    const {classes} = this.props;

    return (

      <div>
        <DonationTable
          data={this.state.donations}
          handleRowClick={this.rowClick}
        />

      </div>
    )
  }
};

export default withStyles(styles)(DonationFeature);