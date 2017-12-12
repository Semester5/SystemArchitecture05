public class BehaviourFindBall implements IBehaviour {

    @Override
    public boolean isActivatable() {
        return false;
    }

    @Override
    public float[] calculateSpeed() {
        return new float[0];
    }
}
