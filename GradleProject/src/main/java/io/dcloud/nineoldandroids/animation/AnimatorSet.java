package io.dcloud.nineoldandroids.animation;

import android.view.animation.Interpolator;
import io.dcloud.nineoldandroids.animation.Animator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class AnimatorSet extends Animator {
    public ArrayList<Animator> mPlayingSet = new ArrayList<>();
    public HashMap<Animator, Node> mNodeMap = new HashMap<>();
    public ArrayList<Node> mNodes = new ArrayList<>();
    public ArrayList<Node> mSortedNodes = new ArrayList<>();
    public boolean mNeedsSort = true;
    public AnimatorSetListener mSetListener = null;
    public boolean mTerminated = false;
    public boolean mStarted = false;
    public long mStartDelay = 0;
    public ValueAnimator mDelayAnim = null;
    public long mDuration = -1;

    public class AnimatorSetListener implements Animator.AnimatorListener {
        public AnimatorSet mAnimatorSet;

        public AnimatorSetListener(AnimatorSet animatorSet) {
            this.mAnimatorSet = animatorSet;
        }

        @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            ArrayList<Animator.AnimatorListener> arrayList;
            AnimatorSet animatorSet = AnimatorSet.this;
            if (animatorSet.mTerminated || animatorSet.mPlayingSet.size() != 0 || (arrayList = AnimatorSet.this.mListeners) == null) {
                return;
            }
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                AnimatorSet.this.mListeners.get(i).onAnimationCancel(this.mAnimatorSet);
            }
        }

        @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            animator.removeListener(this);
            AnimatorSet.this.mPlayingSet.remove(animator);
            boolean z = true;
            ((Node) this.mAnimatorSet.mNodeMap.get(animator)).done = true;
            if (AnimatorSet.this.mTerminated) {
                return;
            }
            ArrayList arrayList = this.mAnimatorSet.mSortedNodes;
            int size = arrayList.size();
            int i = 0;
            while (true) {
                if (i >= size) {
                    break;
                }
                if (!((Node) arrayList.get(i)).done) {
                    z = false;
                    break;
                }
                i++;
            }
            if (z) {
                ArrayList<Animator.AnimatorListener> arrayList2 = AnimatorSet.this.mListeners;
                if (arrayList2 != null) {
                    ArrayList arrayList3 = (ArrayList) arrayList2.clone();
                    int size2 = arrayList3.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        ((Animator.AnimatorListener) arrayList3.get(i2)).onAnimationEnd(this.mAnimatorSet);
                    }
                }
                this.mAnimatorSet.mStarted = false;
            }
        }

        @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    public static class Dependency {
        public static final int AFTER = 1;
        public static final int WITH = 0;
        public Node node;
        public int rule;

        public Dependency(Node node, int i) {
            this.node = node;
            this.rule = i;
        }
    }

    public static class DependencyListener implements Animator.AnimatorListener {
        public AnimatorSet mAnimatorSet;
        public Node mNode;
        public int mRule;

        public DependencyListener(AnimatorSet animatorSet, Node node, int i) {
            this.mAnimatorSet = animatorSet;
            this.mNode = node;
            this.mRule = i;
        }

        private void startIfReady(Animator animator) {
            if (this.mAnimatorSet.mTerminated) {
                return;
            }
            Dependency dependency = null;
            int size = this.mNode.tmpDependencies.size();
            int i = 0;
            while (true) {
                if (i < size) {
                    Dependency dependency2 = this.mNode.tmpDependencies.get(i);
                    if (dependency2.rule == this.mRule && dependency2.node.animation == animator) {
                        animator.removeListener(this);
                        dependency = dependency2;
                        break;
                    }
                    i++;
                } else {
                    break;
                }
            }
            this.mNode.tmpDependencies.remove(dependency);
            if (this.mNode.tmpDependencies.size() == 0) {
                this.mNode.animation.start();
                this.mAnimatorSet.mPlayingSet.add(this.mNode.animation);
            }
        }

        @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.mRule == 1) {
                startIfReady(animator);
            }
        }

        @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (this.mRule == 0) {
                startIfReady(animator);
            }
        }
    }

    public static class Node implements Cloneable {
        public Animator animation;
        public ArrayList<Dependency> dependencies = null;
        public ArrayList<Dependency> tmpDependencies = null;
        public ArrayList<Node> nodeDependencies = null;
        public ArrayList<Node> nodeDependents = null;
        public boolean done = false;

        public Node(Animator animator) {
            this.animation = animator;
        }

        public void addDependency(Dependency dependency) {
            if (this.dependencies == null) {
                this.dependencies = new ArrayList<>();
                this.nodeDependencies = new ArrayList<>();
            }
            this.dependencies.add(dependency);
            if (!this.nodeDependencies.contains(dependency.node)) {
                this.nodeDependencies.add(dependency.node);
            }
            Node node = dependency.node;
            if (node.nodeDependents == null) {
                node.nodeDependents = new ArrayList<>();
            }
            node.nodeDependents.add(this);
        }

        /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
        public Node m764clone() {
            try {
                Node node = (Node) super.clone();
                node.animation = this.animation.m763clone();
                return node;
            } catch (CloneNotSupportedException unused) {
                throw new AssertionError();
            }
        }
    }

    private void sortNodes() {
        if (!this.mNeedsSort) {
            int size = this.mNodes.size();
            for (int i = 0; i < size; i++) {
                Node node = this.mNodes.get(i);
                ArrayList<Dependency> arrayList = node.dependencies;
                if (arrayList != null && arrayList.size() > 0) {
                    int size2 = node.dependencies.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        Dependency dependency = node.dependencies.get(i2);
                        if (node.nodeDependencies == null) {
                            node.nodeDependencies = new ArrayList<>();
                        }
                        if (!node.nodeDependencies.contains(dependency.node)) {
                            node.nodeDependencies.add(dependency.node);
                        }
                    }
                }
                node.done = false;
            }
            return;
        }
        this.mSortedNodes.clear();
        ArrayList arrayList2 = new ArrayList();
        int size3 = this.mNodes.size();
        for (int i3 = 0; i3 < size3; i3++) {
            Node node2 = this.mNodes.get(i3);
            ArrayList<Dependency> arrayList3 = node2.dependencies;
            if (arrayList3 == null || arrayList3.size() == 0) {
                arrayList2.add(node2);
            }
        }
        ArrayList arrayList4 = new ArrayList();
        while (arrayList2.size() > 0) {
            int size4 = arrayList2.size();
            for (int i4 = 0; i4 < size4; i4++) {
                Node node3 = (Node) arrayList2.get(i4);
                this.mSortedNodes.add(node3);
                ArrayList<Node> arrayList5 = node3.nodeDependents;
                if (arrayList5 != null) {
                    int size5 = arrayList5.size();
                    for (int i5 = 0; i5 < size5; i5++) {
                        Node node4 = node3.nodeDependents.get(i5);
                        node4.nodeDependencies.remove(node3);
                        if (node4.nodeDependencies.size() == 0) {
                            arrayList4.add(node4);
                        }
                    }
                }
            }
            arrayList2.clear();
            arrayList2.addAll(arrayList4);
            arrayList4.clear();
        }
        this.mNeedsSort = false;
        if (this.mSortedNodes.size() != this.mNodes.size()) {
            throw new IllegalStateException("Circular dependencies cannot exist in AnimatorSet");
        }
    }

    @Override // io.dcloud.nineoldandroids.animation.Animator
    public void cancel() {
        this.mTerminated = true;
        if (isStarted()) {
            ArrayList arrayList = null;
            ArrayList<Animator.AnimatorListener> arrayList2 = this.mListeners;
            if (arrayList2 != null) {
                ArrayList arrayList3 = (ArrayList) arrayList2.clone();
                Iterator it = arrayList3.iterator();
                while (it.hasNext()) {
                    ((Animator.AnimatorListener) it.next()).onAnimationCancel(this);
                }
                arrayList = arrayList3;
            }
            ValueAnimator valueAnimator = this.mDelayAnim;
            if (valueAnimator != null && valueAnimator.isRunning()) {
                this.mDelayAnim.cancel();
            } else if (this.mSortedNodes.size() > 0) {
                Iterator<Node> it2 = this.mSortedNodes.iterator();
                while (it2.hasNext()) {
                    it2.next().animation.cancel();
                }
            }
            if (arrayList != null) {
                Iterator it3 = arrayList.iterator();
                while (it3.hasNext()) {
                    ((Animator.AnimatorListener) it3.next()).onAnimationEnd(this);
                }
            }
            this.mStarted = false;
        }
    }

    @Override // io.dcloud.nineoldandroids.animation.Animator
    public void end() {
        this.mTerminated = true;
        if (isStarted()) {
            if (this.mSortedNodes.size() != this.mNodes.size()) {
                sortNodes();
                for (Node node : this.mSortedNodes) {
                    if (this.mSetListener == null) {
                        this.mSetListener = new AnimatorSetListener(this);
                    }
                    node.animation.addListener(this.mSetListener);
                }
            }
            ValueAnimator valueAnimator = this.mDelayAnim;
            if (valueAnimator != null) {
                valueAnimator.cancel();
            }
            if (this.mSortedNodes.size() > 0) {
                Iterator<Node> it = this.mSortedNodes.iterator();
                while (it.hasNext()) {
                    it.next().animation.end();
                }
            }
            ArrayList<Animator.AnimatorListener> arrayList = this.mListeners;
            if (arrayList != null) {
                Iterator it2 = ((ArrayList) arrayList.clone()).iterator();
                while (it2.hasNext()) {
                    ((Animator.AnimatorListener) it2.next()).onAnimationEnd(this);
                }
            }
            this.mStarted = false;
        }
    }

    public ArrayList<Animator> getChildAnimations() {
        ArrayList<Animator> arrayList = new ArrayList<>();
        Iterator<Node> it = this.mNodes.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().animation);
        }
        return arrayList;
    }

    @Override // io.dcloud.nineoldandroids.animation.Animator
    public long getDuration() {
        return this.mDuration;
    }

    @Override // io.dcloud.nineoldandroids.animation.Animator
    public long getStartDelay() {
        return this.mStartDelay;
    }

    @Override // io.dcloud.nineoldandroids.animation.Animator
    public boolean isRunning() {
        Iterator<Node> it = this.mNodes.iterator();
        while (it.hasNext()) {
            if (it.next().animation.isRunning()) {
                return true;
            }
        }
        return false;
    }

    @Override // io.dcloud.nineoldandroids.animation.Animator
    public boolean isStarted() {
        return this.mStarted;
    }

    public Builder play(Animator animator) {
        if (animator == null) {
            return null;
        }
        this.mNeedsSort = true;
        return new Builder(animator);
    }

    public void playSequentially(Animator... animatorArr) {
        if (animatorArr != null) {
            this.mNeedsSort = true;
            int i = 0;
            if (animatorArr.length == 1) {
                play(animatorArr[0]);
                return;
            }
            while (i < animatorArr.length - 1) {
                Builder builderPlay = play(animatorArr[i]);
                i++;
                builderPlay.before(animatorArr[i]);
            }
        }
    }

    public void playTogether(Animator... animatorArr) {
        if (animatorArr != null) {
            this.mNeedsSort = true;
            Builder builderPlay = play(animatorArr[0]);
            for (int i = 1; i < animatorArr.length; i++) {
                builderPlay.with(animatorArr[i]);
            }
        }
    }

    @Override // io.dcloud.nineoldandroids.animation.Animator
    public void setInterpolator(Interpolator interpolator) {
        Iterator<Node> it = this.mNodes.iterator();
        while (it.hasNext()) {
            it.next().animation.setInterpolator(interpolator);
        }
    }

    @Override // io.dcloud.nineoldandroids.animation.Animator
    public void setStartDelay(long j) {
        this.mStartDelay = j;
    }

    @Override // io.dcloud.nineoldandroids.animation.Animator
    public void setTarget(Object obj) {
        Iterator<Node> it = this.mNodes.iterator();
        while (it.hasNext()) {
            Animator animator = it.next().animation;
            if (animator instanceof AnimatorSet) {
                ((AnimatorSet) animator).setTarget(obj);
            } else if (animator instanceof ObjectAnimator) {
                ((ObjectAnimator) animator).setTarget(obj);
            }
        }
    }

    @Override // io.dcloud.nineoldandroids.animation.Animator
    public void setupEndValues() {
        Iterator<Node> it = this.mNodes.iterator();
        while (it.hasNext()) {
            it.next().animation.setupEndValues();
        }
    }

    @Override // io.dcloud.nineoldandroids.animation.Animator
    public void setupStartValues() {
        Iterator<Node> it = this.mNodes.iterator();
        while (it.hasNext()) {
            it.next().animation.setupStartValues();
        }
    }

    @Override // io.dcloud.nineoldandroids.animation.Animator
    public void start() {
        this.mTerminated = false;
        this.mStarted = true;
        sortNodes();
        int size = this.mSortedNodes.size();
        for (int i = 0; i < size; i++) {
            Node node = this.mSortedNodes.get(i);
            ArrayList<Animator.AnimatorListener> listeners = node.animation.getListeners();
            if (listeners != null && listeners.size() > 0) {
                for (Animator.AnimatorListener animatorListener : new ArrayList(listeners)) {
                    if ((animatorListener instanceof DependencyListener) || (animatorListener instanceof AnimatorSetListener)) {
                        node.animation.removeListener(animatorListener);
                    }
                }
            }
        }
        final ArrayList<Node> arrayList = new ArrayList();
        for (int i2 = 0; i2 < size; i2++) {
            Node node2 = this.mSortedNodes.get(i2);
            if (this.mSetListener == null) {
                this.mSetListener = new AnimatorSetListener(this);
            }
            ArrayList<Dependency> arrayList2 = node2.dependencies;
            if (arrayList2 == null || arrayList2.size() == 0) {
                arrayList.add(node2);
            } else {
                int size2 = node2.dependencies.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    Dependency dependency = node2.dependencies.get(i3);
                    dependency.node.animation.addListener(new DependencyListener(this, node2, dependency.rule));
                }
                node2.tmpDependencies = (ArrayList) node2.dependencies.clone();
            }
            node2.animation.addListener(this.mSetListener);
        }
        if (this.mStartDelay <= 0) {
            for (Node node3 : arrayList) {
                node3.animation.start();
                this.mPlayingSet.add(node3.animation);
            }
        } else {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            this.mDelayAnim = valueAnimatorOfFloat;
            valueAnimatorOfFloat.setDuration(this.mStartDelay);
            this.mDelayAnim.addListener(new AnimatorListenerAdapter() { // from class: io.dcloud.nineoldandroids.animation.AnimatorSet.1
                public boolean canceled = false;

                @Override // io.dcloud.nineoldandroids.animation.AnimatorListenerAdapter, io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    this.canceled = true;
                }

                @Override // io.dcloud.nineoldandroids.animation.AnimatorListenerAdapter, io.dcloud.nineoldandroids.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    if (this.canceled) {
                        return;
                    }
                    int size3 = arrayList.size();
                    for (int i4 = 0; i4 < size3; i4++) {
                        Node node4 = (Node) arrayList.get(i4);
                        node4.animation.start();
                        AnimatorSet.this.mPlayingSet.add(node4.animation);
                    }
                }
            });
            this.mDelayAnim.start();
        }
        ArrayList<Animator.AnimatorListener> arrayList3 = this.mListeners;
        if (arrayList3 != null) {
            ArrayList arrayList4 = (ArrayList) arrayList3.clone();
            int size3 = arrayList4.size();
            for (int i4 = 0; i4 < size3; i4++) {
                ((Animator.AnimatorListener) arrayList4.get(i4)).onAnimationStart(this);
            }
        }
        if (this.mNodes.size() == 0 && this.mStartDelay == 0) {
            this.mStarted = false;
            ArrayList<Animator.AnimatorListener> arrayList5 = this.mListeners;
            if (arrayList5 != null) {
                ArrayList arrayList6 = (ArrayList) arrayList5.clone();
                int size4 = arrayList6.size();
                for (int i5 = 0; i5 < size4; i5++) {
                    ((Animator.AnimatorListener) arrayList6.get(i5)).onAnimationEnd(this);
                }
            }
        }
    }

    @Override // io.dcloud.nineoldandroids.animation.Animator
    /* JADX INFO: renamed from: clone */
    public AnimatorSet m763clone() {
        AnimatorSet animatorSet = (AnimatorSet) super.m763clone();
        animatorSet.mNeedsSort = true;
        animatorSet.mTerminated = false;
        animatorSet.mStarted = false;
        animatorSet.mPlayingSet = new ArrayList<>();
        animatorSet.mNodeMap = new HashMap<>();
        animatorSet.mNodes = new ArrayList<>();
        animatorSet.mSortedNodes = new ArrayList<>();
        HashMap map = new HashMap();
        for (Node node : this.mNodes) {
            Node nodeM764clone = node.m764clone();
            map.put(node, nodeM764clone);
            animatorSet.mNodes.add(nodeM764clone);
            animatorSet.mNodeMap.put(nodeM764clone.animation, nodeM764clone);
            ArrayList arrayList = null;
            nodeM764clone.dependencies = null;
            nodeM764clone.tmpDependencies = null;
            nodeM764clone.nodeDependents = null;
            nodeM764clone.nodeDependencies = null;
            ArrayList<Animator.AnimatorListener> listeners = nodeM764clone.animation.getListeners();
            if (listeners != null) {
                for (Animator.AnimatorListener animatorListener : listeners) {
                    if (animatorListener instanceof AnimatorSetListener) {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(animatorListener);
                    }
                }
                if (arrayList != null) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        listeners.remove((Animator.AnimatorListener) it.next());
                    }
                }
            }
        }
        for (Node node2 : this.mNodes) {
            Node node3 = (Node) map.get(node2);
            ArrayList<Dependency> arrayList2 = node2.dependencies;
            if (arrayList2 != null) {
                for (Dependency dependency : arrayList2) {
                    node3.addDependency(new Dependency((Node) map.get(dependency.node), dependency.rule));
                }
            }
        }
        return animatorSet;
    }

    @Override // io.dcloud.nineoldandroids.animation.Animator
    public AnimatorSet setDuration(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("duration must be a value of zero or greater");
        }
        Iterator<Node> it = this.mNodes.iterator();
        while (it.hasNext()) {
            it.next().animation.setDuration(j);
        }
        this.mDuration = j;
        return this;
    }

    public void playTogether(Collection<Animator> collection) {
        if (collection == null || collection.size() <= 0) {
            return;
        }
        this.mNeedsSort = true;
        Builder builderPlay = null;
        for (Animator animator : collection) {
            if (builderPlay == null) {
                builderPlay = play(animator);
            } else {
                builderPlay.with(animator);
            }
        }
    }

    public class Builder {
        public Node mCurrentNode;

        public Builder(Animator animator) {
            Node node = (Node) AnimatorSet.this.mNodeMap.get(animator);
            this.mCurrentNode = node;
            if (node == null) {
                this.mCurrentNode = new Node(animator);
                AnimatorSet.this.mNodeMap.put(animator, this.mCurrentNode);
                AnimatorSet.this.mNodes.add(this.mCurrentNode);
            }
        }

        public Builder after(Animator animator) {
            Node node = (Node) AnimatorSet.this.mNodeMap.get(animator);
            if (node == null) {
                node = new Node(animator);
                AnimatorSet.this.mNodeMap.put(animator, node);
                AnimatorSet.this.mNodes.add(node);
            }
            this.mCurrentNode.addDependency(new Dependency(node, 1));
            return this;
        }

        public Builder before(Animator animator) {
            Node node = (Node) AnimatorSet.this.mNodeMap.get(animator);
            if (node == null) {
                node = new Node(animator);
                AnimatorSet.this.mNodeMap.put(animator, node);
                AnimatorSet.this.mNodes.add(node);
            }
            node.addDependency(new Dependency(this.mCurrentNode, 1));
            return this;
        }

        public Builder with(Animator animator) {
            Node node = (Node) AnimatorSet.this.mNodeMap.get(animator);
            if (node == null) {
                node = new Node(animator);
                AnimatorSet.this.mNodeMap.put(animator, node);
                AnimatorSet.this.mNodes.add(node);
            }
            node.addDependency(new Dependency(this.mCurrentNode, 0));
            return this;
        }

        public Builder after(long j) {
            ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
            valueAnimatorOfFloat.setDuration(j);
            after(valueAnimatorOfFloat);
            return this;
        }
    }

    public void playSequentially(List<Animator> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        this.mNeedsSort = true;
        int i = 0;
        if (list.size() == 1) {
            play(list.get(0));
            return;
        }
        while (i < list.size() - 1) {
            Builder builderPlay = play(list.get(i));
            i++;
            builderPlay.before(list.get(i));
        }
    }
}
