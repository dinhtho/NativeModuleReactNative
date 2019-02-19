import React, { Component } from 'react'
import { Text, View, TouchableOpacity, NativeModules, Image } from 'react-native'
// Styles
import styles from './Styles/LaunchScreenStyles'
const NativeToast = NativeModules.NativeToast
const Camera = NativeModules.Camera

export default class LaunchScreen extends Component {
  constructor(props) {
    super(props)
    this.state = {
      uri: null
    }
    this.openCamera =this.openCamera.bind(this)
  }

  render() {
    return (
      <View
        style={styles.mainContainer}>
        <TouchableOpacity
          style={{ backgroundColor: 'red', padding: 10 }}
          onPress={this.toast} >
          <Text>Toast</Text>
        </TouchableOpacity>

        <TouchableOpacity
          style={{ backgroundColor: 'red', padding: 10 }}
          onPress={this.openCamera} >
          <Text>Take a Photo</Text>
        </TouchableOpacity>

        <Image style={{ width: 200, height: 200,marginTop:10 }}
          source={{ uri: this.state.uri }} />
      </View>
    )
  }

  toast() {
    NativeToast.showMessage('My Native Toast', NativeToast.SHORT);
  }
  openCamera() {
    Camera.takePhoto((response) => {
      NativeToast.showMessage(response.path, NativeToast.SHORT);
      
      this.setState({
        uri: response.uri
      })
    })
  }
}
